package com.docapp.springjwt.payload.request;

public class ConversazioneRequest {
    private String studente2;
    private String studente1;
    private String id;
    private String nome_conversazione;

    public ConversazioneRequest() {
    }

    public ConversazioneRequest(String id, String studente1, String studente2, String nome_conversazione) {
        this.studente2 = studente2;
        this.studente1 = studente1;
        this.id = id;
        this.nome_conversazione = nome_conversazione;
    }

    public String getStudente2() {
        return studente2;
    }

    public void setStudente2(String studente2) {
        this.studente2 = studente2;
    }

    public String getStudente1() {
        return studente1;
    }

    public void setStudente1(String studente1) {
        this.studente1 = studente1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome_conversazione() {
        return nome_conversazione;
    }

    public void setNome_conversazione(String nome_conversazione) {
        this.nome_conversazione = nome_conversazione;
    }
}

