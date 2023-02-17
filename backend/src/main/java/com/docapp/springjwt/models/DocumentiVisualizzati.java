package com.docapp.springjwt.models;



import javax.persistence.*;

@Entity
@Table(name = "documentiVisualizzati")
public class DocumentiVisualizzati {

    @EmbeddedId
    private DocumentiVisualizzatiId id;

    @ManyToOne
    @MapsId("documento")
    @JoinColumn(name = "documento")
    private Documento documento;

    @ManyToOne
    @MapsId("studente")
    @JoinColumn(name = "studente")
    private User studente;

    public DocumentiVisualizzati() {
    }

    public DocumentiVisualizzati(Documento documento, User studente) {
        this.documento = documento;
        this.studente = studente;
        this.id = new DocumentiVisualizzatiId(documento.getId(), studente.getId());
    }

    public DocumentiVisualizzatiId getId() {
        return id;
    }

    public void setId(DocumentiVisualizzatiId id) {
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
