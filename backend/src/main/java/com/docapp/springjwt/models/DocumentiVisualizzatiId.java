package com.docapp.springjwt.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DocumentiVisualizzatiId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long documento;

    private Long studente;

    public DocumentiVisualizzatiId() {
    }

    public DocumentiVisualizzatiId(Long documento, Long studente) {
        this.documento = documento;
        this.studente = studente;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public Long getStudente() {
        return studente;
    }

    public void setStudente(Long studente) {
        this.studente = studente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentiVisualizzatiId)) return false;
        DocumentiVisualizzatiId that = (DocumentiVisualizzatiId) o;
        return Objects.equals(getDocumento(), that.getDocumento()) &&
                Objects.equals(getStudente(), that.getStudente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumento(), getStudente());
    }
}
