package org.covid19.helper.curatedList.Dao;

import org.covid19.helper.curatedList.DTO.RequestSearchDTO;
import org.covid19.helper.curatedList.Entity.DataCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class DataExtractorDao{

     private final Integer TOP_QUERY = 50;
//
//    public List<DataCard> getDataCard(RequestSearchDTO requestSearchDTO){
//        String where = requestSearchDTO.getSearchString();
//        String query = "select * from data_card " + where + " order by rating desc limit " + String.valueOf(TOP_QUERY);
//
//        return null;
//    }

    @Autowired
    EntityManager entityManager;


    public List<DataCard> getCards(RequestSearchDTO requestSearchDTO) {

        String where = requestSearchDTO.getSearchString();
        String orderBy = " order by rating desc limit " + String.valueOf(TOP_QUERY);
        String projection = "select d.* from data_card d ";
        String q = projection + where + orderBy;
        Query query = entityManager.createNativeQuery(q,DataCard.class);
        return query.getResultList();

    }
}
