package com.docapp.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "messaggio")
public class Messaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversazione_id", referencedColumnName = "id")
    private Conversazione conversazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_studente", referencedColumnName = "id")
    private User studente;

    @NotBlank
    @Column(name = "testo", columnDefinition = "TEXT")
    private String testo;

    @Column(name = "timestamp_msg", columnDefinition = "DATETIME")
    private LocalDateTime timestamp;

    public Messaggio() {
    }

    public Messaggio(Conversazione conversazione, User studente, String testo, LocalDateTime timestamp) {
        this.conversazione = conversazione;
        this.studente = studente;
        this.testo = testo;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conversazione getConversazione() {
        return conversazione;
    }

    public void setConversazione(Conversazione conversazione) {
        this.conversazione = conversazione;
    }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
