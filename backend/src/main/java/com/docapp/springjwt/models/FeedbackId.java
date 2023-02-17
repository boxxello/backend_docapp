package com.docapp.springjwt.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FeedbackId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long documentoId;

    private Long studenteId;

    public FeedbackId() {
    }

    public FeedbackId(Long documentoId, Long studenteId) {
        this.documentoId = documentoId;
        this.studenteId = studenteId;
    }

    public Long getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }

    public Long getStudenteId() {
        return studenteId;
    }

    public void setStudenteId(Long studenteId) {
        this.studenteId = studenteId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FeedbackId)) {
            return false;
        }
        FeedbackId other = (FeedbackId) o;
        return Objects.equals(documentoId, other.documentoId)
                && Objects.equals(studenteId, other.studenteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentoId, studenteId);
    }
}
