package com.docapp.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**

 A class representing a user of the system.
 */
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private transient List<Documento> documenti = new ArrayList<>();
    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private transient List<DocumentiVisualizzati> documentiVisualizzati = new ArrayList<>();
    /**
     Default constructor for User class.
     */
    public User() {
    }
    /**
     Constructor for User class.
     @param username the username of the user
     @param email the email address of the user
     @param password the password of the user
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Documento> getDocumenti() {
        return documenti;
    }

    public void addDocumento(Documento documento) {
        documenti.add(documento);
        documento.setStudente(this);
    }

    public void removeDocumento(Documento documento) {
        documenti.remove(documento);
        documento.setStudente(null);
    }
    public List<DocumentiVisualizzati> getDocumentiVisualizzati() {
        return documentiVisualizzati;
    }

    public void setDocumentiVisualizzati(List<DocumentiVisualizzati> documentiVisualizzati) {
        this.documentiVisualizzati = documentiVisualizzati;
    }

    public void addDocumentoVisualizzato(Documento documento) {
        DocumentiVisualizzati dv = new DocumentiVisualizzati(this, documento);
        documentiVisualizzati.add(dv);
        System.out.println(documentiVisualizzati.get(0).toString());

    }

    public void removeDocumentoVisualizzato(Documento documento) {
        for (DocumentiVisualizzati dv : documentiVisualizzati) {
            if (dv.getDocumento().equals(documento)) {
                documentiVisualizzati.remove(dv);
                dv.setStudente(null);
                break;
            }
        }
    }

}
