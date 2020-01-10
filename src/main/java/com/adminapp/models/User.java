package com.adminapp.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {
    @Id
    @Column(name = "iduser")
    private Integer idUser;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "created")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated")
    @LastModifiedDate
    private Date updatedAt;
}
