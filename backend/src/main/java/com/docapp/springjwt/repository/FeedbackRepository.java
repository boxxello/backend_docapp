/**
 The FeedbackRepository interface is a Spring Data JPA repository for managing Feedback entities.
 It provides methods for CRUD operations and querying the database for Feedback entities.
 */
package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Returns a list of all Feedback entities associated with a given Documento.
     *
     * @param documento the Documento to retrieve Feedback for
     * @return a list of Feedback entities associated with the given Documento
     */
    List<Feedback> findAllByDocumento(Documento documento);

    /**
     * Returns a list of all Feedback entities associated with a given Documento ID.
     *
     * @param documentoId the ID of the Documento to retrieve Feedback for
     * @return a list of Feedback entities associated with the given Documento ID
     */
    List<Feedback> findAllByDocumentoId(Long documentoId);
}
