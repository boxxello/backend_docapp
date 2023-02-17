package com.docapp.shared_docapp.models;

import java.util.HashMap;

public class Conversazione implements IEntity {
    public static final String TABLE_NAME ="Conversazione";
    private String nome_conversazione,
     studente1,studente2;

    private String id_conversazione ;

    public Conversazione(){}

    public Conversazione(String nome_conversazione, String studente1, String studente2, String id_conversazione) {
        this.nome_conversazione = nome_conversazione;
        this.studente1 = studente1;
        this.studente2 = studente2;
        this.id_conversazione = id_conversazione;
    }

    public String getNome_conversazione() {
        return nome_conversazione;
    }

    public void setNome_conversazione(String nome_conversazione) {
        this.nome_conversazione = nome_conversazione;
    }

    public String getStudente1() {
        return studente1;
    }

    public void setStudente1(String studente1) {
        this.studente1 = studente1;
    }

    public String getStudente2() {
        return studente2;
    }

    public void setStudente2(String studente2) {
        this.studente2 = studente2;
    }

    public String getId_conversazione() {
        return id_conversazione;
    }

    public void setId_conversazione(String id_conversazione) {
        this.id_conversazione = id_conversazione;
    }

    @Override
    public HashMap<String, ?> toHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id_conversazione);
        map.put("studente1", studente1);
        map.put("studente2", studente2);
        map.put("nome_conversazione", nome_conversazione);
        return map;
    }
}
