package com.docapp.springjwt.models;

import javax.persistence.*;
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


    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "universita", length = 40, nullable = false)
    private String universita;

    @Column(name = "facolta", length = 40, nullable = false)
    private String facolta;

    @Column(name = "corso_di_studio", length = 30, nullable = false)
    private String corsoDiStudio;

    @Column(name = "dimensione", nullable = false)
    private Long dimensione;

    public Documento() {
    }

    public Documento(String nome, String descrizione, String universita, String facolta, String corsoDiStudio, Long dimensione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.universita = universita;
        this.facolta = facolta;
        this.corsoDiStudio = corsoDiStudio;
        this.dimensione = dimensione;
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

    public String getUniversita() {
        return universita;
    }

    public void setUniversita(String universita) {
        this.universita = universita;
    }

    public String getFacolta() {
        return facolta;
    }

    public void setFacolta(String facolta) {
        this.facolta = facolta;
    }

    public String getCorsoDiStudio() {
        return corsoDiStudio;
    }

    public void setCorsoDiStudio(String corsoDiStudio) {
        this.corsoDiStudio = corsoDiStudio;
    }

    public Long getDimensione() {
        return dimensione;
    }

    public void setDimensione(Long dimensione) {
        this.dimensione = dimensione;
    }
}