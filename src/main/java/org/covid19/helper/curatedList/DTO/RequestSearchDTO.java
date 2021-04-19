package org.covid19.helper.curatedList.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

@Data
@NoArgsConstructor
public class RequestSearchDTO {

    private Boolean isSeeker;

    private String city;

    private String state;

    private String requestType;

    private String q;

    public RequestSearchDTO(Boolean isSeeker, String city, String state,
                             String requestType,
                             String q) {
        this.isSeeker = isSeeker;
        this.city = city;
        this.state = state;
        this.requestType = requestType;
        this.q = q;
    }

    public String getSearchString() {
        if (this.isSeeker == null
                && this.city == null
                && this.state == null
                && this.requestType == null
                && this.q == null) {
            return "";
        }
        String q = " where ";
        if (this.isSeeker != null) {
                q = " is_seeker = " + this.isSeeker + " and";
        }
        if(this.city != null){
            q = " city = " + this.city + " and";
        }
        if(this.state != null){
            q = " state = " + this.state + " and";
        }
        if(this.requestType != null){
            q = " request_type " + this.requestType + " and";
        }
        if(this.q != null){
            q = "( name like '%" +this.q +"%' or phone like '%" + this.q +
                    "%' + address like '%" + this.q + "%' and";
        }
       return q.substring(0,q.length()-4);
    }
}
