package com.adminapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@Table(name = "users")
public class UserModel extends Audit{
    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<UserRoleModel> roles;

}
