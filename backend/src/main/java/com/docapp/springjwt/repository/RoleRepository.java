package com.docapp.springjwt.repository;

import java.util.Optional;

import com.docapp.springjwt.models.ERole;
import com.docapp.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  /**

   Retrieves an optional Role object based on its name.
   @param name the name of the role to be retrieved.
   @return an optional Role object corresponding to the name passed as parameter, or an empty optional if the role is not found.
   */
  Optional<Role> findByName(ERole name);
}
