package com.docapp.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "corso_di_studio")
public class CorsoDiStudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "universita_id")
    @JsonIgnore
    private Universita universita;

    public CorsoDiStudio() {
    }

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