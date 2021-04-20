package org.covid19.helper.curatedList.Repository;


import org.covid19.helper.curatedList.Entity.MasterCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCityRepository extends JpaRepository<MasterCity,Long> {

}
