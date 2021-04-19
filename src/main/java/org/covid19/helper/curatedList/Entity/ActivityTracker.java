package org.covid19.helper.curatedList.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ActivityTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cookie;

    private String ip;

    private String event;

    private Date createdDate;

    public ActivityTracker(String cookie, String ip, String event){
        this.cookie = cookie;
        this.ip = ip;
        this.event = event;
        this.createdDate = new Date();
    }
}
