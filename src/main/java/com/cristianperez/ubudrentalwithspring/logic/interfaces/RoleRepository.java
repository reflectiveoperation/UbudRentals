package com.cristianperez.ubudrentalwithspring.logic.interfaces;

import com.cristianperez.ubudrentalwithspring.logic.models.Role;
import com.cristianperez.ubudrentalwithspring.logic.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
