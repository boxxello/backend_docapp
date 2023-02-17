package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversitaRepository extends JpaRepository<Universita, Long> {

    List<Universita> findAllByNome(String nome);
}

