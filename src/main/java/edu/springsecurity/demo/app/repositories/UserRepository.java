package edu.springsecurity.demo.app.repositories;

import edu.springsecurity.demo.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String userName);
}
