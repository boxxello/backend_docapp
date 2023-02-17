package com.docapp.springjwt.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InterazionePostId implements Serializable {

    private Long postId;

    private Long studenteId;

    public InterazionePostId() {
    }

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