package com.github.bruce.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

//    private List<String> roles;

    private Date registerTime;

}
