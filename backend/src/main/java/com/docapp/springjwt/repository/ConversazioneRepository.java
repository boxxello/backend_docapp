/**

 The ConversazioneRepository interface is a Spring Data JPA repository for the Conversazione entity.
 It provides methods for performing CRUD operations and custom queries on Conversazione entities.
 @author Francesco Bosso
 */
package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversazioneRepository extends JpaRepository<Conversazione, Long> {

    /**
     * Retrieves a list of Conversazione entities for the specified User.
     *
     * @param user the User entity to search for
     * @return a list of Conversazione entities associated with the specified User
     */
    @Query("SELECT c FROM Conversazione c WHERE c.studente1 = :user OR c.studente2 = :user")
    List<Conversazione> findAllByStudente1OrStudente2(@Param("user") User user);


    /**
     * Retrieves the Conversazione entity with the specified id and User as one of the participants.
     *
     * @param conversazioneId the id of the Conversazione entity to retrieve
     * @param studente        the User entity that must be one of the participants of the retrieved Conversazione
     * @return an Optional containing the retrieved Conversazione entity, or an empty Optional if no entity was found
     */
    @Query("SELECT c FROM Conversazione c WHERE c.id = :conversazioneId AND (c.studente1 = :studente OR c.studente2 = :studente)")
    Optional<Conversazione> findByIdAndStudenti1OrStudente2(Long conversazioneId, User studente);

    /**
     * Retrieves the Conversazione entity with the specified Users as participants.
     *
     * @param currentUser the User entity that must be one of the participants of the retrieved Conversazione
     * @param studente2   the other User entity that must be one of the participants of the retrieved Conversazione
     * @return an Optional containing the retrieved Conversazione entity, or an empty Optional if no entity was found
     */
    Optional<Conversazione> findByStudente1AndStudente2(User currentUser, User studente2);
}
