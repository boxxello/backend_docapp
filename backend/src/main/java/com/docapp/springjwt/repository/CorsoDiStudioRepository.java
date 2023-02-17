package com.docapp.springjwt.repository;


import com.docapp.springjwt.models.CorsoDiStudio;
import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoDiStudioRepository extends JpaRepository<CorsoDiStudio, Long> {

    CorsoDiStudio findByNome(String nome);
    List<CorsoDiStudio> findAllByUniversita(Universita universita);
    List<CorsoDiStudio> findAllByUniversitaId(Long id);

}
