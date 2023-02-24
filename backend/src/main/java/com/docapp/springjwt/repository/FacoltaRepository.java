package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Facolta;
import com.docapp.springjwt.models.Universita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacoltaRepository extends JpaRepository<Facolta, Long> {

        /**
         * Returns the Facolta entity with the given name.
         *
         * @param nome The name of the Facolta entity to retrieve.
         * @return The Facolta entity with the given name.
         */
        Facolta findByNome(String nome);

        /**
         * Returns a list of all Facolta entities belonging to the Universita with the given ID.
         *
         * @param id The ID of the Universita to retrieve Facolta entities for.
         * @return A list of all Facolta entities belonging to the Universita with the given ID.
         */
        List<Facolta> findAllByUniversitaId(Long id);

        /**
         * Returns a list of all Facolta entities belonging to the given Universita entity.
         *
         * @param universita The Universita entity to retrieve Facolta entities for.
         * @return A list of all Facolta entities belonging to the given Universita entity.
         */
        List<Facolta> findAllByUniversita(Universita universita);
}
