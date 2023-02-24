package com.docapp.springjwt.models;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

/**

 The DocumentiVisualizzati class represents a record of a document viewed by a student.
 It contains the record's unique identifier, the student who viewed the document, and the document itself.
 */
@Entity
@Table(name = "documentivisualizzati")
public class DocumentiVisualizzati {
    /**
     The unique identifier of the record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     The student who viewed the document.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studente_id")
    private User studente;
    /**
     The document viewed by the student.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documento_id")
    private Documento documento;
    /**
     Default constructor.
     */
    public DocumentiVisualizzati() {
    }
    /**
     Constructor that initializes the student who viewed the document and the document itself.
     @param studente The student who viewed the document.
     @param documento The document viewed by the student.
     */
    public DocumentiVisualizzati(User studente, Documento documento) {
        this.studente = studente;
        this.documento = documento;
    }
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
