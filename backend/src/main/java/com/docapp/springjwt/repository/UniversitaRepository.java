package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversitaRepository extends JpaRepository<Universita, Long> {

    Optional<List<Universita>> findAllByNome(String nome);

    Optional<Universita> findByNome(String nome);

    Optional<List<Universita>> findAllByNomeLike(String nome);
}

