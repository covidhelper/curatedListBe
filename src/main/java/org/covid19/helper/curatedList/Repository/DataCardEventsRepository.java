package org.covid19.helper.curatedList.Repository;

import org.covid19.helper.curatedList.Entity.DataCardEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCardEventsRepository extends JpaRepository<DataCardEvent,Long> {

//    @Query("from DataCardEvents where dataCardId = :dataCardId and userId =:userId and action =:action" +
//            " and createdDate < DATE_SI ")
//    private List<DataCardEvents> findDataCardEventsBy
//
//     List<DataCardEvent> findByDateCardEventsByUserId(String userId);

     List<DataCardEvent> findDataCardEventByDataCardId(Long dataCardId);
}
