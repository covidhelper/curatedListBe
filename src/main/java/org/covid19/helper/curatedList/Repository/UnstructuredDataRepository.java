package org.covid19.helper.curatedList.Repository;

import org.covid19.helper.curatedList.Entity.UnstructuredData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnstructuredDataRepository extends JpaRepository<UnstructuredData,Long> {

}
