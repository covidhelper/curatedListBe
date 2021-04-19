package org.covid19.helper.curatedList.Repository;

import org.covid19.helper.curatedList.Entity.DataCard;
import org.covid19.helper.curatedList.Entity.UserCookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataCardRepository extends JpaRepository<DataCard,Long> {

    public List<DataCard> findDataCardByUid(String uid);

    public List<DataCard> findDataCardByUuid(String uuid);

    public List<DataCard> findAllByOrderByRatingDesc();

}
