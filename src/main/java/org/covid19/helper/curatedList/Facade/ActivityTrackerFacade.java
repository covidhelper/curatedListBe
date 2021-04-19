package org.covid19.helper.curatedList.Facade;

import org.apache.catalina.User;
import org.covid19.helper.curatedList.DTO.RequestExtractorDTO;
import org.covid19.helper.curatedList.Entity.UserCookie;
import org.covid19.helper.curatedList.Service.ActivityTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityTrackerFacade {

    @Autowired
    ActivityTrackerService activityTrackerService;

    public UserCookie saveUserAndActivity(String cookie, String ip, String type){
        return activityTrackerService.saveUserAndActivity(cookie,ip,type);
    }

    public UserCookie saveUserAndActivity(RequestExtractorDTO requestExtractorDTO, String type){
       return saveUserAndActivity(requestExtractorDTO.getCookie(),requestExtractorDTO.getIpAddress(),type);
    }
}
