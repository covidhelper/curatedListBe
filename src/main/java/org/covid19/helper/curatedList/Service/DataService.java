package org.covid19.helper.curatedList.Service;

import org.covid19.helper.curatedList.Entity.DataCard;
import org.covid19.helper.curatedList.Entity.DataCardEvent;
import org.covid19.helper.curatedList.Entity.MasterCity;
import org.covid19.helper.curatedList.Entity.UnstructuredData;
import org.covid19.helper.curatedList.Repository.DataCardEventsRepository;
import org.covid19.helper.curatedList.Repository.DataCardRepository;
import org.covid19.helper.curatedList.Repository.MasterCityRepository;
import org.covid19.helper.curatedList.Repository.UnstructuredDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    UnstructuredDataRepository udr;

    @Autowired
    DataCardRepository dataCardRepository;

    @Autowired
    DataCardEventsRepository dataCardEventsRepository;

    @Autowired
    MasterCityRepository masterCityRepository;

    public void saveUnstructuredFileData(UnstructuredData unstructuredData) {
        udr.save(unstructuredData);
    }

    public void saveDataCard(DataCard dataCard) {
        DataCard dbDataCard = getDataCardByUid(dataCard.getUid());
        if (dbDataCard == null) {
            dataCardRepository.save(dataCard);
            dataCard.setIsSave(Boolean.TRUE);
            return;
        }
        dataCard.setIsSave(Boolean.FALSE);
    }

    public DataCard getDataCardByUid(String uid) {
        List<DataCard> dataCardList = dataCardRepository.findDataCardByUid(uid);
        if (dataCardList == null || dataCardList.size() == 0) {
            return null;
        }
        return dataCardList.get(0);
    }

    public DataCard getDataCardByUuid(String uuid) {
        List<DataCard> dataCards = dataCardRepository.findDataCardByUuid(uuid);
        if (dataCards == null || dataCards.size() == 0) {
            return null;
        }
        return dataCards.get(0);
    }

    public List<DataCard> getDataCards() {
        return dataCardRepository.findAllByOrderByRatingDesc();
    }

    public void saveDataCardEvents(DataCardEvent dataCardEvent) {
        dataCardEventsRepository.save(dataCardEvent);
    }

    public List<DataCardEvent> getDataCardEventsByCardId(Long cardId) {
        return dataCardEventsRepository.findDataCardEventByDataCardId(cardId);
    }

    public List<MasterCity> getCities() {
        return masterCityRepository.findAll();
    }

    public Boolean saveCity(String city) {
        MasterCity masterCity = new MasterCity();
        masterCity.setCity(city);
        try {
            masterCityRepository.save(masterCity);
        } catch (Exception ex) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


}
