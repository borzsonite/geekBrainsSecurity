package edu.springsecurity.demo.app.repositories;

import edu.springsecurity.demo.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
