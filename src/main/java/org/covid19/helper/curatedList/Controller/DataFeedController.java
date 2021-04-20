package org.covid19.helper.curatedList.Controller;

import lombok.extern.slf4j.Slf4j;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;
import org.covid19.helper.curatedList.DTO.*;

import org.covid19.helper.curatedList.Entity.MasterCity;
import org.covid19.helper.curatedList.Facade.DataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/v1/data")
public class DataFeedController {

    @Autowired
    DataFacade dataFacade;

    @RequestMapping(value = "unstructured", method = RequestMethod.POST)
    public ResultDTO saveUnstructuredData(@RequestBody UnstructuredDTO unstructuredDTO,
                                          HttpServletResponse httpServletResponse,
                                          HttpServletRequest httpServletRequest) {
        return null;
    }

    @RequestMapping(value = "file", method = RequestMethod.POST)
    public ResultDTO saveFileData(@RequestParam("file") MultipartFile multipartFile,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws Exception {
        return new ResultDTO(dataFacade.saveFile(multipartFile, httpServletRequest, httpServletResponse));
    }

    @RequestMapping(value = "structured", method = RequestMethod.POST)
    public ResultDTO saveStructuredData(@RequestBody StructuredDTO structuredDTO,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) {
        List<DataCardDTO> dataCardDTOList = dataFacade.saveStructuredData(structuredDTO,
                httpServletRequest, httpServletResponse);
        Map<String,Object> map = new HashMap<>();
        map.put(ApplicationConstants.DATACARDS,dataCardDTOList);
        return new ResultDTO(map);
    }

    @RequestMapping(value = "structured/action", method = RequestMethod.POST)
    public ResultDTO saveActionForDataCard(@RequestBody DataCardEventDTO dataCardEventDTO,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse){
        Map<String,Object> map = new HashMap<>();
        map.put(ApplicationConstants.DATACARD,dataFacade.saveActionForCard(
                dataCardEventDTO,httpServletRequest,httpServletResponse));
        return new ResultDTO(map);
    }

    @RequestMapping(value = "structured", method = RequestMethod.GET)
    public ResultDTO getDataCards(
                                  @RequestParam(required = false) Boolean isSeeker,
                                  @RequestParam(required = false) String city,
                                  @RequestParam(required = false) String state,
                                  @RequestParam(required = false) String requestType,
                                  @RequestParam(required = false) String q,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse){
        RequestSearchDTO requestSearchDTO = new RequestSearchDTO(isSeeker,
                city,state,requestType,q);
        List<DataCardDTO> dataCardDTOS = dataFacade.getDataCards(requestSearchDTO);
        Map<String,Object> result = new HashMap<>();
        result.put(ApplicationConstants.DATACARDS,dataCardDTOS);
        return new ResultDTO(result);
    }

    @RequestMapping(value = "structured/action/{uuid}" , method = RequestMethod.GET)
    public ResultDTO getActionForDataCard(@PathVariable String uuid){
        List<DataCardEventDTO> dataCardEvents = dataFacade.getDataCardEvents(uuid);
        Map<String,Object> result = new HashMap<>();
        result.put(ApplicationConstants.DATACARDEVENT,dataCardEvents);
        return new ResultDTO(result);
    }

    @RequestMapping(value = "structured/{uuid}" , method = RequestMethod.GET)
    public ResultDTO getDataCard(@PathVariable String uuid){
        DataCardDTO dataCardDTO = dataFacade.getDataCardByUuid(uuid);
        Map<String,Object> result = new HashMap<>();
        result.put(ApplicationConstants.DATACARD,dataCardDTO);
        return new ResultDTO(result);
    }

    @RequestMapping(value = "cities", method = RequestMethod.GET)
    public ResultDTO getCitites(){
        List<MasterCity> masterCities = dataFacade.getCities();
        Map<String,Object> map = new HashMap<>();
        map.put(ApplicationConstants.CITY,masterCities);
        return new ResultDTO(map);
    }

    @RequestMapping(value = "city/{city}", method = RequestMethod.POST)
    public ResultDTO saveCity(@PathVariable  String city){
        Boolean status = dataFacade.saveCity(city);
        return status? new ResultDTO(): new ResultDTO("City Already Added",400);
    }

    @RequestMapping(value ="/structured/{uuid}" , method = RequestMethod.DELETE)
    public ResultDTO deleteData(@PathVariable String uuid){
        return dataFacade.deleteData(uuid)?new ResultDTO(): new
                ResultDTO("Not deleted", 400);
    }

}
