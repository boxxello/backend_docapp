/**

 This class represents a login request payload that contains the user's username and password.
 */
package com.docapp.springjwt.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    /**
     * The username provided by the user for authentication.
     */
    @NotBlank
    private String username;

    /*private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

    /**
     * The password provided by the user for authentication.
     */
    @NotBlank
    private String password;

    /**
     * Returns the username provided by the user for authentication.
     *
     * @return The username provided by the user for authentication.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Sets the username provided by the user for authentication.
     *
     * @param username The username provided by the user for authentication.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password provided by the user for authentication.
     *
     * @return The password provided by the user for authentication.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password provided by the user for authentication.
     *
     * @param password The password provided by the user for authentication.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
