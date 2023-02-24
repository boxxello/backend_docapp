package com.docapp.springjwt.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**

 The FeedbackId class represents the composite primary key for the Feedback entity.
 It contains the ids of both the document and the student who gave the feedback.
 */
@Embeddable
public class FeedbackId implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     The id of the document associated with the feedback.
     */
    private Long documentoId;
    /**
     The id of the student who gave the feedback.
     */
    private Long studenteId;
    /**
     Constructs a new FeedbackId object with no arguments.
     */
    public FeedbackId() {
    }
    /**
     Constructs a new FeedbackId object with the given document and student ids.
     @param documentoId The id of the document associated with the feedback.
     @param studenteId The id of the student who gave the feedback.
     */
    public FeedbackId(Long documentoId, Long studenteId) {
        this.documentoId = documentoId;
        this.studenteId = studenteId;
    }
    /**
     Returns the id of the document associated with the feedback.
     @return The id of the document associated with the feedback.
     */
    public Long getDocumentoId() {
        return documentoId;
    }
    /**
     Sets the id of the document associated with the feedback.
     @param documentoId The id of the document associated with the feedback.
     */
    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }
    /**
     Returns the id of the student who gave the feedback.
     @return The id of the student who gave the feedback.
     */
    public Long getStudenteId() {
        return studenteId;
    }
    /**
     Sets the id of the student who gave the feedback.
     @param studenteId The id of the student who gave the feedback.
     */
    public void setStudenteId(Long studenteId) {
        this.studenteId = studenteId;
    }
    /**
     Compares this FeedbackId object to another object to check if they are equal.
     @param o The object to compare to this FeedbackId.
     @return true if the objects are equal, false otherwise.
     */
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
    /**
     Returns the hash code for this FeedbackId object.
     @return The hash code for this FeedbackId object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(documentoId, studenteId);
    }
}