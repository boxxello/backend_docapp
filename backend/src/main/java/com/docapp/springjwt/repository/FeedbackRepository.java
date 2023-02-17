package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findAllByDocumento(Documento documento);
    List<Feedback> findAllByDocumentoId(Long documentoId);
}
