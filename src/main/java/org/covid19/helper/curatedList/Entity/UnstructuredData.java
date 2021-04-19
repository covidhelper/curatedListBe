package org.covid19.helper.curatedList.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class UnstructuredData extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String userId;

    private String documentName;

    private String comments;

    private String url;

    private String link;

    private Boolean isGiver = Boolean.TRUE;

    private String extension;

    private Boolean isCurated;

    private Boolean isVerified;
}

