package com.docapp.springjwt.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**

 The InterazionePostId class represents the composite primary key for an InterazionePost entity,
 consisting of the ids of the post and the student.
 */

@Embeddable
public class InterazionePostId implements Serializable {
    /**
     The id of the post associated with this interaction.
     */
    private Long postId;
    /**
     The id of the student who made this interaction.
     */
    private Long studenteId;
    /**
     Constructs a new InterazionePostId object with no arguments.
     */
    public InterazionePostId() {
    }
    /**
     Constructs a new InterazionePostId object with the given post id and student id.
     @param postId The id of the post associated with this interaction.
     @param studenteId The id of the student who made this interaction.
     */
    public InterazionePostId(Long postId, Long studenteId) {
        this.postId = postId;
        this.studenteId = studenteId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getStudenteId() {
        return studenteId;
    }

    public void setStudenteId(Long studenteId) {
        this.studenteId = studenteId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterazionePostId that)) return false;
        return Objects.equals(getPostId(), that.getPostId()) &&
                Objects.equals(getStudenteId(), that.getStudenteId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getStudenteId());
    }
}