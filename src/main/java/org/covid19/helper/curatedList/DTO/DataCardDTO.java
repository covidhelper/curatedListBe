package org.covid19.helper.curatedList.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataCardDTO {

    private String userId;

    private String uuid;

    private String city;

    private String state;

    private String email;

    private ApplicationConstants.RequestType requestType;

    private String address;

    private String name;

    private String comment;

    private String phone;

    private String contactPerson;

    private Boolean isGiver;

    private Boolean isSave;

    private Date lastReported;

    private ApplicationConstants.DataCardAction action;

    private Double rating = 0.0;

    private Integer totalCount = 0;
}
