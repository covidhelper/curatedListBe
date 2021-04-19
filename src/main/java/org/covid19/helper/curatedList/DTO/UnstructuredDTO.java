package org.covid19.helper.curatedList.DTO;

import lombok.Data;

@Data
public class UnstructuredDTO {

    private String cookie;

    private String ip;

    private String documentName;

    //FE
    private String comments;

    private String extension;

    //FE
    private String uuid;

    private String url;

    //FE
    private Boolean isGiver;

    public void setRequestAttributes(RequestExtractorDTO requestExtractorDTO){
        this.cookie = requestExtractorDTO.getCookie();
        this.ip = requestExtractorDTO.getIpAddress();
    }

}
