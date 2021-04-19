package org.covid19.helper.curatedList.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(name = "cookie_idx" , columnList = "cookie" , unique = true) })
@Getter
@Setter
@NoArgsConstructor
public class UserCookie extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ip;

    private String cookie;

    private Integer count = 1;

    public void setCount(){
        this.count += this.count + 1;
    }

    public UserCookie(String cookie,String ip){
        this.ip = ip;
        this.cookie = cookie;
    }

}
