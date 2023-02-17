package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Facolta;
import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacoltaRepository extends JpaRepository<Facolta, Long> {

        Facolta findByNome(String nome);
        List<Facolta> findAllByUniversitaId(Long id);
        List<Facolta> findAllByUniversita(Universita universita);
}
