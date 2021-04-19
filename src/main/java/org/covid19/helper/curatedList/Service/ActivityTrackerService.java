package org.covid19.helper.curatedList.Service;

import org.covid19.helper.curatedList.Entity.ActivityTracker;
import org.covid19.helper.curatedList.Entity.UserCookie;
import org.covid19.helper.curatedList.Repository.ActivityTrackerRepository;
import org.covid19.helper.curatedList.Repository.UserCookieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTrackerService {

    @Autowired
    ActivityTrackerRepository atr;

    @Autowired
    UserCookieRepository userCookier;

    public UserCookie saveUserCookie(String cookie, String ip) {
        UserCookie userCookie = findUserCookieByCookie(cookie);
        if (userCookie != null) {
            userCookie.setCount();
        } else {
            userCookie = new UserCookie(cookie, ip);
        }
        return userCookier.save(userCookie);
    }

    public UserCookie findUserCookieByCookie(String cookie) {
        List<UserCookie> userCookieList = userCookier.findUserCookieByCookie(cookie);
        if (userCookieList == null || userCookieList.size() == 0) {
            return null;
        }
        return userCookieList.get(0);
    }

    public UserCookie saveUserAndActivity(String cookie, String ip, String event){
        saveActivity(cookie,ip,event);
        return saveUserCookie(cookie,ip);
    }


    public void saveActivity(String cookie, String ip, String event) {
        ActivityTracker at = new ActivityTracker(cookie, ip, event);
        saveActivity(at);
    }

    public void saveActivity(ActivityTracker at) {
        atr.save(at);
    }

}
