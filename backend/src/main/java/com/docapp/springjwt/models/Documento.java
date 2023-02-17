package com.docapp.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 70)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    //regex per descrizione
    @Pattern(regexp = "^[A-Za-z0-9.,; ]*$", message = "Descrizione cannot contain special characters")
    @Size(max = 650)
    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "universita_id")
    private Universita universita;


    @Column(name = "dimensione", nullable = false)
    private Long dimensione;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studente_id")
    private User studente;

    public Documento() {
    }

    public Documento(String nome, String descrizione, Universita universita, Long dimensione, User studente) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.universita = universita;
        this.dimensione = dimensione;
        this.studente = studente;
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
