package com.adminapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users_roles")
public class UserRoleModel extends Audit{

    @EmbeddedId
    private UserRolePkModel id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @MapsId("idUser")
    @JoinColumn(name = "ur_iduser")
    UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @MapsId("idRol")
    @JoinColumn(name = "ur_idrol")
    RolModel rol;

    @Column(name = "status")
    private String status;

}
