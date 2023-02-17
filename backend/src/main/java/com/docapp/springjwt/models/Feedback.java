package com.docapp.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @EmbeddedId
    private FeedbackId id;

    @ManyToOne
    @MapsId("documentoId")
    private Documento documento;

    @ManyToOne
    @MapsId("studenteId")
    private User studente;
    public Feedback() {
    }
    public Feedback(FeedbackId id, Documento documento, User studente) {
        this.id = id;
        this.documento = documento;
        this.studente = studente;
    }

    public FeedbackId getId() {
        return id;
    }

    public void setId(FeedbackId id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }
}
