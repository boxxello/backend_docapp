package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {



    List<Documento> findAllByUser(Long userId);
    List<Documento> findAllByUser(User user);
}
