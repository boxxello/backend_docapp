/**

 The MessaggioRepository interface extends JpaRepository and provides methods to interact with Messaggio entities
 */
package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessaggioRepository extends JpaRepository<Messaggio, Long> {

    /**
     * Returns a list of messages that belong to a specific conversation
     *
     * @param conversazioneId the id of the conversation
     * @return a list of messages belonging to the conversation
     */
    List<Messaggio> findAllByConversazione_Id(Long conversazioneId);

    /**
     * Returns a message by its id and the id of its conversation
     *
     * @param messaggioId the id of the message
     * @param conversazioneId the id of the conversation
     * @return an optional message belonging to the conversation
     */
    Optional<Messaggio> findByIdAndConversazione_Id(Long messaggioId, Long conversazioneId);


    /**
     * Returns a list of messages that belong to a specific conversation
     *
     * @param conversazione the conversation
     * @return a list of messages belonging to the conversation
     */
    List<Messaggio> findByConversazione(Conversazione conversazione);
}
