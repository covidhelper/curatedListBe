package org.covid19.helper.curatedList.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultDTO {

    public Boolean success;

    public String message;

    public Object response;

    public Integer errorCode;

    public ResultDTO(String message, Object response) {
        this.success = Boolean.TRUE;
        this.response = response;
        this.message = message;
    }

    public ResultDTO(Object response) {
        this.response = response;
        this.success = Boolean.TRUE;
        this.message = ApplicationConstants.SUCCESS;
    }

    public ResultDTO() {
        this.response = new HashMap<>();
        this.success = Boolean.TRUE;
        this.message = ApplicationConstants.SUCCESS;
    }

    public ResultDTO(String message, Integer errorCode) {
        this.success = Boolean.FALSE;
        this.response = new HashMap<>();
        this.message = message;
        this.errorCode = errorCode;
    }

    public ResultDTO(String message, Integer errorCode, Object response) {
        this.success = Boolean.FALSE;
        this.response = response;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ResultDTO(Boolean status,String keyName, Object response){
        this.success = Boolean.TRUE;
        HashMap<String,Object> res = new HashMap<>();
        res.put(keyName,response);
        this.response = res;
        this.message = ApplicationConstants.SUCCESS;

    }

    public ResultDTO(Boolean status, Object response, String message){
        this.success = status;
        this.response = response;
        this.message = message;
    }

}
