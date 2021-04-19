package org.covid19.helper.curatedList.DTO;

import lombok.Data;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import java.util.Date;

@Data
public class DataCardEventDTO {

    private String uuid;

    private ApplicationConstants.DataCardAction action;

    private String comment;

    private Date createdDate;

}
