package com.docapp.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "universita")
public class Universita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "universita")
    @JsonIgnore
    private List<Documento> documenti;

    @OneToMany(mappedBy = "universita")
    private List<CorsoDiStudio> corsiDiStudio;

    @OneToMany(mappedBy = "universita")
    private List<Facolta> facolta;

    @NotBlank
    @Size(max = 40)
    @Column(name = "nome")
    private String nome;

    public Universita() {

    }

    public Universita( String nome, List<Documento> documenti, List<CorsoDiStudio> corsiDiStudio, List<Facolta> facolta) {
        this.documenti = documenti;
        this.corsiDiStudio = corsiDiStudio;
        this.facolta = facolta;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Documento> getDocumenti() {
        return documenti;
    }

    public void setDocumenti(List<Documento> documenti) {
        this.documenti = documenti;
    }

    public List<CorsoDiStudio> getCorsiDiStudio() {
        return corsiDiStudio;
    }

    public void setCorsiDiStudio(List<CorsoDiStudio> corsiDiStudio) {
        this.corsiDiStudio = corsiDiStudio;
    }

    public List<Facolta> getFacolta() {
        return facolta;
    }

    public void setFacolta(List<Facolta> facolta) {
        this.facolta = facolta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
