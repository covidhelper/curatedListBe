package org.covid19.helper.curatedList.DTO;

import lombok.Data;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import java.util.Date;
import java.util.List;

@Data
public class StructuredDTO {

    private String city;

    private String state;

    private String email;

    private ApplicationConstants.RequestType requestType;

    private String address;

    private String name;

    private String comment;

    private String newCity;

    private List<ContactDTO> contacts;

    private Boolean isGiver;



}
