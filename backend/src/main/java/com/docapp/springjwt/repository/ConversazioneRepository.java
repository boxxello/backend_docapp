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

    @Query("SELECT c FROM Conversazione c WHERE c.studente1 = :user OR c.studente2 = :user")
    List<Conversazione> findAllByStudente1OrStudente2(@Param("user") User user);


    @Query("SELECT c FROM Conversazione c WHERE c.id = :conversazioneId AND (c.studente1 = :studente OR c.studente2 = :studente)")
    Optional<Conversazione> findByIdAndStudenti1OrStudente2(Long conversazioneId, User studente);

    Optional<Conversazione> findByStudente1AndStudente2(User currentUser, User studente2);
}
