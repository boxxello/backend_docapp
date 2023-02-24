package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    /**
     Retrieves a list of Documento objects for a given student ID.
     @param userId the ID of the student
     @return a list of Documento objects
     */
    List<Documento> findAllByStudenteId(Long userId);

    /**

     Retrieves a list of Documento objects for a given student.
     @param user the student
     @return an optional list of Documento objects
     */
    Optional<List<Documento>> findAllByStudente(User user);

    /**
     Retrieves a Documento object for a given ID and student.
     @param id the ID of the Documento object
     @param currentUser the current user (student)
     @return an optional Documento object
     */
    Optional<Documento> findByIdAndStudente(Long id, User currentUser);
}
