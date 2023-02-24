/**

 Repository interface for managing DocumentiVisualizzati entities in the database.
 */
package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.DocumentiVisualizzati;
import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentiVisualizzatiRepository  extends JpaRepository<DocumentiVisualizzati, Long> {

    /**
     * Returns a list of all DocumentiVisualizzati entities associated with the given Documento.
     *
     * @param documento the Documento to retrieve DocumentiVisualizzati entities for
     * @return a list of all DocumentiVisualizzati entities associated with the given Documento
     */
    List<DocumentiVisualizzati> findAllByDocumento(Documento documento);

    /**
     * Returns a list of all DocumentiVisualizzati entities associated with the given User.
     *
     * @param studente the User to retrieve DocumentiVisualizzati entities for
     * @return a list of all DocumentiVisualizzati entities associated with the given User
     */
    List<DocumentiVisualizzati> findByStudente(User studente);

}
