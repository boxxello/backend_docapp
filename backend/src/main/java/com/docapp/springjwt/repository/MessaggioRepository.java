package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessaggioRepository extends JpaRepository<Messaggio, Long> {

    List<Messaggio> findAllByConversazione_Id(Long conversazioneId);

    Optional<Messaggio> findByIdAndConversazione_Id(Long messaggioId, Long conversazioneId);


    List<Messaggio> findByConversazione(Conversazione conversazione);
}
