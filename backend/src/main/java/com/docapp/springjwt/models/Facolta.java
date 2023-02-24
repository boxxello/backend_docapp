package com.docapp.springjwt.models;

import javax.persistence.*;

/**

 The Facolta class represents a faculty within a university.
 */
@Entity
@Table(name = "facolta")
public class Facolta {
    /**
     The unique identifier of the faculty.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     The name of the faculty.
     */
    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    /**
     The university that the faculty belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "universita_id")
    private Universita universita;
    /**
     Default constructor.
     */
    public Facolta() {
    }
    /**
     Constructor that initializes the name and university of the faculty.
     @param nome the name of the faculty
     @param universita the university that the faculty belongs to
     */
    public Facolta(String nome, Universita universita) {
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