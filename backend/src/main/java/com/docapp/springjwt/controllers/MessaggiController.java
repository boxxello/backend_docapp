package com.docapp.springjwt.controllers;

import com.docapp.springjwt.exceptions.ResourceNotFoundException;
import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.Messaggio;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.repository.ConversazioneRepository;
import com.docapp.springjwt.repository.MessaggioRepository;
import com.docapp.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messaggi")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class MessaggiController {

    @Autowired
    private MessaggioRepository messaggioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversazioneRepository conversazioneRepository;

    @GetMapping("/{conversazioneId}")
    public List<Messaggio> getMessaggiByConversazione(@PathVariable Long conversazioneId,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //check if the user is part of the conversation
        Conversazione conversazione = (Conversazione) conversazioneRepository.findByIdAndStudenti1OrStudente2(conversazioneId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
        return messaggioRepository.findAllByConversazione_Id(conversazioneId);
    }

    @GetMapping("/{conversazioneId}/{messaggioId}")
    public Messaggio getMessaggioById(@PathVariable Long conversazioneId, @PathVariable Long messaggioId,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Conversazione conversazione = (Conversazione) conversazioneRepository.findByIdAndStudenti1OrStudente2(conversazioneId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));

        return messaggioRepository.findByIdAndConversazione_Id(messaggioId, conversazioneId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }

    @PostMapping("/{conversazioneId}")
    public Messaggio createMessaggio(@PathVariable Long conversazioneId, @RequestBody Messaggio messaggio,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Conversazione conversazione = (Conversazione) conversazioneRepository.findByIdAndStudenti1OrStudente2(conversazioneId, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
        if(messaggio.getTesto().length()<=0)
            throw new ResourceNotFoundException("Message is empty");
        messaggio.setConversazione(conversazione);
        messaggio.setStudente(currentUser);
        messaggio.setTimestamp(LocalDateTime.now());

        return messaggioRepository.save(messaggio);
    }
}


