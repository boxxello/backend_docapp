/**

 This class represents a signup request payload that contains the user's username, email, password, and role(s).
 */
package com.docapp.springjwt.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

  /**

   The username provided by the user during registration.
   */
  @NotBlank
  @Size(min = 6, max = 16)
  private String username;

  /**

   The email address provided by the user during registration.
   */
  @NotBlank
  @Size(max = 40)
  @Email
  private String email;

  /**

   The role(s) assigned to the user during registration.
   */
  private Set<String> role;

  /**

   The password provided by the user during registration.
   */
  @NotBlank
  @Size(min = 6, max = 64)
  private String password;

  /**

   Returns the username provided by the user during registration.
   @return The username provided by the user during registration.
   */
  public String getUsername() {
    return username;
  }

  /**

   Sets the username provided by the user during registration.
   @param username The username provided by the user during registration.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**

   Returns the email address provided by the user during registration.
   @return The email address provided by the user during registration.
   */
  public String getEmail() {
    return email;
  }

  /**

   Sets the email address provided by the user during registration.
   @param email The email address provided by the user during registration.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**

   Returns the password provided by the user during registration.
   @return The password provided by the user during registration.
   */
  public String getPassword() {
    return password;
  }

  /**

   Sets the password provided by the user during registration.
   @param password The password provided by the user during registration.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**

   Returns the role(s) assigned to the user during registration.
   @return The role(s) assigned to the user during registration.
   */
  public Set<String> getRole() {
    return this.role;
  }

  /**

   Sets the role(s) assigned to the user during registration.
   @param role The role(s) assigned to the user during registration.
   */
  public void setRole(Set<String> role) {
    this.role = role;
  }
}