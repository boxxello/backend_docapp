package com.docapp.springjwt.models;


import javax.persistence.*;

/**

 The InterazionePost class represents an interaction between a post and a student user.
 It contains a composite primary key consisting of the ids of the post and the student.
 */
@Entity
@Table(name = "interazione_post")
public class InterazionePost {
    /**
     The composite primary key for the InterazionePost entity, consisting of the ids of the post and the student.
     */
    @EmbeddedId
    private InterazionePostId id;
    /**
     The Post that this interaction is associated with.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    private Post post;
    /**
     The User who made this interaction.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studenteId")
    private User studente;
    /**
     Constructs a new InterazionePost object with no arguments.
     */
    public InterazionePost() {
    }
    /**
     Constructs a new InterazionePost object with the given Post and User.
     @param post The Post that this interaction is associated with.
     @param studente The User who made this interaction.
     */
    public InterazionePost(Post post, User studente) {
        this.post = post;
        this.studente = studente;
        this.id = new InterazionePostId(post.getId(), studente.getId());
    }
}
    public InterazionePostId getId() {
        return id;
    }

    public void setId(InterazionePostId id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }
}