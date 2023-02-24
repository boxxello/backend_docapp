/**

 This class represents a response payload that contains a JSON Web Token (JWT) and the user's information.
 The JWT is used for authentication and authorization purposes.
 */
package com.docapp.springjwt.payload.response;

import java.util.List;

public class JwtResponse {

  /**

   The JWT provided by the server.
   */
  private String token;

  /**

   The type of JWT, which is usually "Bearer".
   */
  private String type = "Bearer";

  /**

   The username of the authenticated user.
   */
  private String username;

  /**

   The email address of the authenticated user.
   */
  private String email;

  /**

   The roles assigned to the authenticated user.
   */
  private List<String> roles;

  /**

   Creates a new JwtResponse object with the provided parameters.
   @param accessToken The JWT provided by the server.
   @param username The username of the authenticated user.
   @param email The email address of the authenticated user.
   @param roles The roles assigned to the authenticated user.
   */
  public JwtResponse(String accessToken, String username, String email, List<String> roles) {
    this.token = accessToken;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  /**

   Returns the JWT provided by the server.
   @return The JWT provided by the server.
   */
  public String getAccessToken() {
    return token;
  }

  /**

   Sets the JWT provided by the server.
   @param accessToken The JWT provided by the server.
   */
  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  /**

   Returns the type of JWT, which is usually "Bearer".
   @return The type of JWT, which is usually "Bearer".
   */
  public String getTokenType() {
    return type;
  }

  /**

   Sets the type of JWT, which is usually "Bearer".
   @param tokenType The type of JWT, which is usually "Bearer".
   */
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  /**

   Returns the email address of the authenticated user.
   @return The email address of the authenticated user.
   */
  public String getEmail() {
    return email;
  }

  /**

   Sets the email address of the authenticated user.
   @param email The email address of the authenticated user.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**

   Returns the username of the authenticated user.
   @return The username of the authenticated user.
   */
  public String getUsername() {
    return username;
  }

  /**

   Sets the username of the authenticated user.
   @param username The username of the authenticated user.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**

   Returns the roles assigned to the authenticated user.
   @return The roles assigned to the authenticated user.
   */
  public List<String> getRoles() {
    return roles;
  }
}