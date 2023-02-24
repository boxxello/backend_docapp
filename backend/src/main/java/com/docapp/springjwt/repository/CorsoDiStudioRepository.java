package com.docapp.springjwt.repository;


import com.docapp.springjwt.models.CorsoDiStudio;
import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoDiStudioRepository extends JpaRepository<CorsoDiStudio, Long> {

    /**
     * Finds a CorsoDiStudio by its name.
     * @param nome the name of the CorsoDiStudio
     * @return the CorsoDiStudio with the given name, or null if not found
     */
    CorsoDiStudio findByNome(String nome);

    /**
     * Finds all CorsoDiStudio entities associated with a given Universita.
     * @param universita the Universita entity to search by
     * @return a List of CorsoDiStudio entities associated with the given Universita
     */
    List<CorsoDiStudio> findAllByUniversita(Universita universita);

    /**
     * Finds all CorsoDiStudio entities whose name contains the given string.
     * @param nome the string to search for in the CorsoDiStudio names
     * @return a List of CorsoDiStudio entities whose name contains the given string
     */
    List<CorsoDiStudio> findByNomeLike(String nome);

    /**
     * Finds all CorsoDiStudio entities associated with a given Universita ID.
     * @param id the ID of the Universita entity to search by
     * @return a List of CorsoDiStudio entities associated with the given Universita ID
     */
    List<CorsoDiStudio> findAllByUniversitaId(Long id);

}
