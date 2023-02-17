package com.docapp.shared_docapp.models;

import java.util.HashMap;

public class Messaggio implements IEntity {
    public static final String TABLE_NAME ="Messaggio";
    private String  testo, tms;
    private long id_conversazione, id_messaggio, id_studente;

    public Messaggio(){}

    public Messaggio(long id_conversazione, long id_studente, String testo, String tms){
        this.id_studente = id_studente;
        this.id_conversazione = id_conversazione;
        this.testo = testo;
        this.tms = tms;
    }



    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getTms() {
        return tms;
    }

    public void setTms(String tms) {
        this.tms = tms;
    }

    public long getId_conversazione() {
        return id_conversazione;
    }

    public void setId_conversazione(long id_conversazione) {
        this.id_conversazione = id_conversazione;
    }

    public long getId_messaggio() {
        return id_messaggio;
    }

    public void setId_messaggio(long id_messaggio) {
        this.id_messaggio = id_messaggio;
    }

    public long getId_studente() {
        return id_studente;
    }

    public void setId_studente(long id_studente) {
        this.id_studente = id_studente;
    }

    @Override
    public HashMap<String, ?> toHashMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id_messaggio);
        map.put("conversazione_id", id_conversazione);
        map.put("id_studente", id_studente);
        map.put("testo", testo);
        map.put("timestamp_msg", tms);
        return map;
    }
}
