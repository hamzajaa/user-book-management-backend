package org.example.userbookmanagementbackend.security.dao;


import org.example.userbookmanagementbackend.security.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
