//package org.covid19.helper.curatedList.Dao;
//
//import org.covid19.helper.curatedList.DTO.RequestSearchDTO;
//import org.covid19.helper.curatedList.Entity.DataCard;
//import org.springframework.jdbc.core.support.JdbcDaoSupport;
//import org.springframework.stereotype.Component;
//import java.util.List;
//
//@Component
//public class DataExtractorDao extends JdbcDaoSupport {
//
//    private final Integer TOP_QUERY = 50;
//
//    public List<DataCard> getDataCard(RequestSearchDTO requestSearchDTO){
//        String where = requestSearchDTO.getSearchString();
//        String query = "select * from data_card " + where + " order by rating desc limit " + String.valueOf(TOP_QUERY);
//
//        return null;
//    }
//}
