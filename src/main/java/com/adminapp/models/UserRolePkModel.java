package com.adminapp.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class UserRolePkModel implements Serializable {

    @Column(name = "ur_iduser")
    private Integer idUser;

    @Column(name = "ur_idrol")
    private Integer idRol;

}
