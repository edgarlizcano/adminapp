package com.adminapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class RolModel extends Audit{
    @Id
    @Column(name = "idrol")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRol;

    @Column(name = "rolname")
    private String rolName;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "rol")
    @JsonManagedReference
    private List<UserRoleModel> users;

}
