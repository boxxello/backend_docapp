package com.docapp.springjwt.models;

import javax.persistence.*;

/**

 The Feedback class represents a feedback given by a student for a document.
 It contains information about the document, the student, and the feedback itself.
 */
@Entity
@Table(name = "feedback")
public class Feedback {
    /**
     The id of the feedback.
     */
    @EmbeddedId
    private FeedbackId id;
    /**
     The document associated with the feedback.
     */
    @ManyToOne
    @MapsId("documentoId")
    private Documento documento;
    /**
     The student who gave the feedback.
     */
    @ManyToOne
    @MapsId("studenteId")
    private User studente;
    /**
     Constructs a new feedback object with no arguments.
     */
    public Feedback() {
    }
    /**
     Constructs a new feedback object with the given id, document, and student.
     @param id The id of the feedback.
     @param documento The document associated with the feedback.
     @param studente The student who gave the feedback.
     */
    public Feedback(FeedbackId id, Documento documento, User studente) {
        this.id = id;
        this.documento = documento;
        this.studente = studente;
    }
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
