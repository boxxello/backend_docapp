package com.docapp.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "documento")
public class Documento {
    public static final String basepath = "uploads/documenti/";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 70)
    @Column(name = "nome")
    private String nome;

    //regex per descrizione
    @Pattern(regexp = "^[A-Za-z0-9.,; ]*$", message = "Descrizione cannot contain special characters")
    @Size(max = 650)
    @NotNull
    @Column(name = "descrizione")
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "universita_id")
    private Universita universita;

    @NotNull
    @Column(name = "dimensione")
    private Long dimensione;


    //we use JsonManagedReference to avoid infinite recursion, but also
    //to show the studente field in the json response
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studente_id")

    private User studente;

    @NotNull
    @Column(name = "path")
    @JsonIgnore
    private String path;


    @NotNull
    @Column(name = "hash")
    private String hash;

    public Documento() {
    }

    public Documento(String nome, String descrizione, Universita universita, Long dimensione, User studente, String path, String hash) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.universita = universita;
        this.dimensione = dimensione;
        this.studente = studente;
        this.path = path;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Universita getUniversita() {
        return universita;
    }


    public void setUniversita(Universita universita) {
        this.universita = universita;
    }

    public Long getDimensione() {
        return dimensione;
    }

    public void setDimensione(Long dimensione) {
        this.dimensione = dimensione;
    }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }


}
