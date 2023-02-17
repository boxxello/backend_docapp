package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.DocumentiVisualizzati;
import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentiVisualizzatiRepository  extends JpaRepository<DocumentiVisualizzati, Long> {

    List<DocumentiVisualizzati> findAllByDocumento(Documento documento);
    List<DocumentiVisualizzati> findByStudente(User studente);

}
