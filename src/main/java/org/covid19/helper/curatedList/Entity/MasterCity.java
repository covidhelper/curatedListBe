package org.covid19.helper.curatedList.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(name = "city_idx" , columnList = "city" , unique = true) })
@Getter
@Setter
@NoArgsConstructor
public class MasterCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

}
