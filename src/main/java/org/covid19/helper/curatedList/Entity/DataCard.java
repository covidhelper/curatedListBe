package org.covid19.helper.curatedList.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {@Index(name = "uid_idx" , columnList = "uid" , unique = true) })
@Getter
@Setter
@NoArgsConstructor
public class DataCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    // city_state_requestType_phone_name
    private String uid;

    private String uuid;

    private String city;

    private String state;

    private String email;

    @Enumerated(EnumType.STRING)
    private ApplicationConstants.RequestType requestType;

    private String address;

    private String name;

    private String comment;

    private String phone;

    private String contactPerson;

    private Boolean isGiver;

    private Double score = 0.0;

    private Date lastReported;

    private Double rating = 0.0;

    private Integer validCount = 0;

    private Integer totalCount = 0;

    private Double denomCount = 0.0;

    @Enumerated(EnumType.STRING)
    private ApplicationConstants.DataCardAction action;

    @Transient
    private Boolean isSave;

    @Transient
    private String newCity;

    public void setUid(){
        this.uid = this.city + "_"  +this.state
                + "_" + this.requestType.name()
                + "_" +this.phone
                + "_" + this.name ;
    }


}
