package com.docapp.springjwt.repository;

import java.util.Optional;

import com.docapp.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /**

   Retrieves a user by their username.
   @param username the username of the user to retrieve
   @return an optional user with the given username, or empty if not found
   */
  Optional<User> findByUsername(String username);

  /**

   Checks if a user with the given username exists.
   @param username the username to check
   @return true if a user with the given username exists, false otherwise
   */
  Boolean existsByUsername(String username);

  /**

   Checks if a user with the given email address exists.
   @param email the email address to check
   @return true if a user with the given email address exists, false otherwise
   */
  Boolean existsByEmail(String email);
}
