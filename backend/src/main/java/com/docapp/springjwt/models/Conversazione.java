package com.docapp.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


import javax.persistence.*;
import java.util.List;
/**

 The Conversazione class represents a conversation between two students.
 It contains the conversation's unique identifier, name, and the two students involved.
 It also has a list of messages exchanged between the two students.
 */
@Entity
@Table(name = "conversazione")
public class Conversazione {
    /**
     The unique identifier of the conversation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     The name of the conversation.
     */
    @Column(name = "nome_conversazione", nullable = false)
    private String nomeConversazione;
    /**
     The first student involved in the conversation.
     */
    @ManyToOne
    @JoinColumn(name = "studente1", nullable = false)
    @JsonIgnore
    private User studente1;
    /**
     The second student involved in the conversation.
     */
    @ManyToOne
    @JoinColumn(name = "studente2", nullable = false)
    private User studente2;
    /**
     A list of messages exchanged between the two students in the conversation.
     */
    @OneToMany(mappedBy = "conversazione", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Messaggio> messaggi;
    /**
     Default constructor.
     */
    public Conversazione() {
    }
    /**
     Constructor that initializes the conversation's name and the two students involved.
     @param nomeConversazione The name of the conversation.
     @param studente1 The first student involved in the conversation.
     @param studente2 The second student involved in the conversation.
     */
    public Conversazione(String nomeConversazione, User studente1, User studente2) {
        this.nomeConversazione = nomeConversazione;
        this.studente1 = studente1;
        this.studente2 = studente2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeConversazione() {
        return nomeConversazione;
    }

    public void setNomeConversazione(String nomeConversazione) {
        this.nomeConversazione = nomeConversazione;
    }

    public User getStudente1() {
        return studente1;
    }

    public void setStudente1(User studente1) {
        this.studente1 = studente1;
    }

    public User getStudente2() {
        return studente2;
    }

    public void setStudente2(User studente2) {
        this.studente2 = studente2;
    }

    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }
}
