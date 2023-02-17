package com.docapp.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "facolta")
public class Facolta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "universita_id")
    private Universita universita;

    public Facolta() {
    }

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