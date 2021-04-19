package org.covid19.helper.curatedList.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class DataCardEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dataCardId;

    private String userId;

    @Enumerated(EnumType.STRING)
    private ApplicationConstants.DataCardAction action;

    private String comment;

    private Date createdDate;

}
