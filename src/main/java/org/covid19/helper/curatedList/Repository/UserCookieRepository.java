package org.covid19.helper.curatedList.Repository;

import org.covid19.helper.curatedList.Entity.UserCookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserCookieRepository extends JpaRepository<UserCookie,Long> {

    public List<UserCookie> findUserCookieByCookie(String cookie);

}
