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


    List<Documento> findAllByStudenteId(Long userId);

    List<Documento> findAllByStudente(User user);

    Optional<Documento> findByIdAndStudente(Long id, User currentUser);
}
