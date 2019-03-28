package com.cristianperez.ubudrentalwithspring.logic.interfaces;

import com.cristianperez.ubudrentalwithspring.logic.models.Token;
import com.cristianperez.ubudrentalwithspring.logic.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByFirstName(String username);
}
