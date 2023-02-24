package com.docapp.springjwt.models;

import javax.persistence.*;

/**

 Represents a role that can be assigned to a user.
 */
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  /**

   Constructs a new Role object.
   */
  public Role() {
  }
  public Role(ERole name) {
    this.name = name;
  }

  /**

   Returns the ID of the role.
   @return the ID of the role.
   */
  public Integer getId() {
    return id;
  }
  /**

   Sets the ID of the role.
   @param id the ID of the role.
   */
  public void setId(Integer id) {
    this.id = id;
  }
  /**

   Returns the name of the role.
   @return the name of the role.
   */
  public ERole getName() {
    return name;
  }
  /**

   Sets the name of the role.
   @param name the name of the role.
   */
  public void setName(ERole name) {
    this.name = name;
  }
}

