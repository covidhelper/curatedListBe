package org.covid19.helper.curatedList.DTO;

import lombok.Data;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

@Data
public class DataCardEventDTO {

    private String uuid;

    private ApplicationConstants.DataCardAction action;

    private String comment;

}
