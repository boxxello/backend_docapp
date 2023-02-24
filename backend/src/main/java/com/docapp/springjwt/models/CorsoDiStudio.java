package com.docapp.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**

 The CorsoDiStudio class represents a study course within a university.
 */
@Entity
@Table(name = "corso_di_studio")
public class CorsoDiStudio {
    /**
     The unique identifier of the study course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     The name of the study course.
     */
    @NotBlank
    @Size(max = 30)
    @Column(name = "nome")
    private String nome;
    /**
     The university that offers the study course.
     */
    @ManyToOne
    @JoinColumn(name = "universita_id")
    @JsonIgnore
    private Universita universita;
    /**
     Default constructor.
     */
    public CorsoDiStudio() {
    }
    /**
     Constructor that initializes the name and university of the study course.
     @param nome the name of the study course
     @param universita the university that offers the study course
     */
    public CorsoDiStudio(String nome, Universita universita) {
        this.nome = nome;
        this.universita = universita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Universita getUniversita() {
        return universita;
    }

    public void setUniversita(Universita universita) {
        this.universita = universita;
    }
}