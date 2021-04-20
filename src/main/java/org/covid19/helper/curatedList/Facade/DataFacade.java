package org.covid19.helper.curatedList.Facade;

import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;
import org.covid19.helper.curatedList.DTO.*;
import org.covid19.helper.curatedList.Entity.*;
import org.covid19.helper.curatedList.Service.DataService;
import org.covid19.helper.curatedList.Util.ApplicationUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DataFacade {

    @Autowired
    DataService dataService;

    @Autowired
    FileStorageFacade fileStorageFacade;

    @Autowired
    ActivityTrackerFacade activityTrackerFacade;

    @Autowired
    Mapper mapper;

    public Map<String,String> saveFile(MultipartFile file,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) throws Exception {
            String uuid = ApplicationUtil.entityUuidGenerator();
            String url = fileStorageFacade.uploadFile(file,uuid);

            UnstructuredDTO unstructuredDTO = setUnstructuredDTOByFile(uuid,file);
            RequestExtractorDTO requestExtractorDTO = ApplicationUtil.
                    getRequestAttributes(httpServletRequest,httpServletResponse);
            unstructuredDTO.setRequestAttributes(requestExtractorDTO);

            UserCookie userCookie = activityTrackerFacade.saveUserAndActivity(requestExtractorDTO,
                    ApplicationConstants.EventType.Unstructured.name());

            UnstructuredData unstructuredData = mapFileUnstructure(unstructuredDTO);
            unstructuredData.setUserId(userCookie.getCookie());
            unstructuredData.setUrl(url);

            dataService.saveUnstructuredFileData(unstructuredData);

            Map<String,String> result = new HashMap<>();
            result.put(ApplicationConstants.UUID,uuid);
            result.put(ApplicationConstants.PREVIEW,url);

            return result;

    }

    public UnstructuredDTO saveUnstructuredData(MultipartFile multipartFile,
                                                UnstructuredDTO unstructuredDTO,
                                                HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse){
        return null;
    }

    private UnstructuredDTO setUnstructuredDTOByFile(String uuid, MultipartFile file){
        UnstructuredDTO unstructuredDTO = new UnstructuredDTO();
        unstructuredDTO.setUuid(uuid);
        unstructuredDTO.setDocumentName(file.getOriginalFilename());
        unstructuredDTO.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        return unstructuredDTO;
    }

    public UnstructuredData mapFileUnstructure(UnstructuredDTO unstructuredDTO){
        UnstructuredData unstructuredData = mapper.map(unstructuredDTO,UnstructuredData.class);
        return unstructuredData;
    }

    public DataCardDTO saveActionForCard(DataCardEventDTO dataCardEventDTO,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse){
            DataCard dataCard = dataService.getDataCardByUuid(dataCardEventDTO.getUuid());
            RequestExtractorDTO requestExtractorDTO = ApplicationUtil.
                getRequestAttributes(httpServletRequest,httpServletResponse);

            UserCookie userCookie = activityTrackerFacade.saveUserAndActivity(requestExtractorDTO,
                ApplicationConstants.EventType.Unstructured.name());

            /*
            TODO: Validation for cool off;
             */
            switch (dataCardEventDTO.getAction()){
                case Verify:
                    dataCard.setDenomCount(dataCard.getDenomCount() + 1.0);
                    dataCard.setValidCount(dataCard.getValidCount() + 1);
                    break;
                case Report:
                    dataCard.setDenomCount(dataCard.getDenomCount() + 1.0);
                    break;
                case Unanswered:
                    dataCard.setDenomCount(dataCard.getDenomCount() + 0.3);
                    break;
                case OutOfStock:
                    dataCard.setDenomCount(dataCard.getDenomCount() + 0.5);
                    break;
            }

            dataCard.setTotalCount(dataCard.getTotalCount() + 1);
            dataCard.setRating(5*dataCard.getValidCount()/dataCard.getDenomCount());
            dataCard.setLastReported(new Date());
            dataCard.setAction(dataCardEventDTO.getAction());

            DataCardEvent dataCardEvent = mapper.map(dataCardEventDTO, DataCardEvent.class);
            dataCardEvent.setDataCardId(dataCard.getId());
            dataCardEvent.setUserId(userCookie.getCookie());
            dataCardEvent.setCreatedDate(new Date());
            dataService.saveDataCardEvents(dataCardEvent);

            dataService.saveDataCard(dataCard);

            return mapper.map(dataCard,DataCardDTO.class);
    }

    public List<DataCardDTO> saveStructuredData(StructuredDTO structuredDTO,
                                                HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse){

        RequestExtractorDTO requestExtractorDTO = ApplicationUtil.
                getRequestAttributes(httpServletRequest,httpServletResponse);
        UserCookie userCookie = activityTrackerFacade.saveUserAndActivity(requestExtractorDTO,
                ApplicationConstants.EventType.Structured.name());

        List<ContactDTO> contacts = structuredDTO.getContacts();
        DataCard baseDataCard = mapper.map(structuredDTO,DataCard.class);
        baseDataCard.setUserId(userCookie.getCookie());

        dumpCity(baseDataCard);

        List<DataCard> dataCards = new ArrayList<>();

        for(ContactDTO contactDTO : contacts){
            DataCard dumpDataCard =  mapper.map(baseDataCard,DataCard.class);
            dumpDataCard.setContactPerson(contactDTO.getContactPerson());
            dumpDataCard.setPhone(contactDTO.getPhone());
            dumpDataCard.setUuid(ApplicationUtil.entityUuidGenerator());
            dumpDataCard.setUid();
            dataService.saveDataCard(dumpDataCard);
            dataCards.add(dumpDataCard);
        }
        List<DataCardDTO> dataCardDTOS = new ArrayList<>();
        for(DataCard dataCard: dataCards){
            dataCardDTOS.add(mapper.map(dataCard,DataCardDTO.class));
        }
        return dataCardDTOS;
    }

    public List<DataCardDTO> getDataCards(RequestSearchDTO requestSearchDTO){
        List<DataCard> dataCards = dataService.getDataCards();
        List<DataCardDTO> dataCardList = new ArrayList<>();
        for(DataCard dataCard: dataCards){
            dataCardList.add(mapper.map(dataCard,DataCardDTO.class));
        }
        return dataCardList;
    }

    public List<DataCardEventDTO> getDataCardEvents(String uuid){
        DataCard dataCard = dataService.getDataCardByUuid(uuid);
        List<DataCardEvent> dataCardEvents = dataService.getDataCardEventsByCardId(dataCard.getId());
        List<DataCardEventDTO> dataCardEventDTOS = new ArrayList<>();
        for(DataCardEvent dataCardEvent:  dataCardEvents){
            dataCardEventDTOS.add(mapper.map(dataCardEvent,DataCardEventDTO.class));
        }
        return dataCardEventDTOS;
    }

    public DataCardDTO getDataCardByUuid(String uuid){
        DataCard dataCard = dataService.getDataCardByUuid(uuid);
        return mapper.map(dataCard,DataCardDTO.class);
    }

    public List<MasterCity> getCities(){
        return dataService.getCities();
    }

    public Boolean saveCity(String city){
        return dataService.saveCity(city);
    }

    private void dumpCity(DataCard dataCard){
        if(dataCard.getNewCity()!=null && dataCard.getNewCity() != ""){
            saveCity(dataCard.getNewCity());
            dataCard.setCity(dataCard.getNewCity());
        }

    }




}
