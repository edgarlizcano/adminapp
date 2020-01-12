package com.adminapp.repositories;

import com.adminapp.models.RolModel;
import com.adminapp.models.UserRoleModel;
import com.adminapp.models.UserRolePkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<UserRoleModel, UserRolePkModel> {
}
