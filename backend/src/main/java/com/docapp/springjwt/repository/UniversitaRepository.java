package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversitaRepository extends JpaRepository<Universita, Long> {

    /**

     Retrieves a list of universities with the given name.
     @param nome the name of the university to search for
     @return an optional containing a list of universities with the given name, or empty if none found
     */
    Optional<List<Universita>> findAllByNome(String nome);

    /**

     Retrieves the university with the given name.
     @param nome the name of the university to retrieve
     @return an optional containing the university with the given name, or empty if none found
     */
    Optional<Universita> findByNome(String nome);

    /**

     Retrieves a list of universities whose name contains the given string.
     @param nome the string to search for in university names
     @return an optional containing a list of universities whose name contains the given string, or empty if none found
     */
    Optional<List<Universita>> findAllByNomeLike(String nome);
}

