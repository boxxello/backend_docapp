package com.docapp.springjwt.controllers;

import com.docapp.springjwt.exceptions.ResourceNotFoundException;
import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.Messaggio;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.payload.response.MessageResponse;
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
import java.util.HashMap;
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

    @GetMapping("/get")
    public ResponseEntity<?> getMessaggiByConversazione(@RequestParam String id,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        Long conversazioneId;
        System.out.println(id);
        if(id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Conversation ID is required"));
        }else{
            try{
                conversazioneId=Long.parseLong(id);
            }catch (NumberFormatException e){
                return ResponseEntity.badRequest().body(new MessageResponse("Conversation ID must be a number"));
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //check if the user is part of the conversation
        Conversazione conversazione = (Conversazione) conversazioneRepository.findById(conversazioneId)
                .orElse(null);
        if (conversazione == null) {
            response.put("message", "Conversation not found");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("messages", messaggioRepository.findAllByConversazione_Id(conversazioneId));
        response.put("conversation", conversazione);
        return ResponseEntity.ok(response);

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

    @PostMapping("/add")
    public ResponseEntity<?> createMessaggio(@RequestBody Messaggio messaggio,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        HashMap<String, Object> response = new HashMap<>();

        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        System.out.println(messaggio.getConversazione());
        Long conversazioneId = messaggio.getConversazione().getId();
        if (conversazioneId == null) {
            response.put("message", "Conversation id is null");
            response.put("status", "400");
            response.put("messaggio", null);
            return ResponseEntity.badRequest().body(response);
        } else {

            Conversazione conversazione = (Conversazione) conversazioneRepository.findByIdAndStudenti1OrStudente2(conversazioneId, currentUser)
                    .orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
            if (messaggio.getTesto().length() <= 0)
                throw new ResourceNotFoundException("Message is empty");
            messaggio.setConversazione(conversazione);
            messaggio.setStudente(currentUser);
            messaggio.setTimestamp(LocalDateTime.now());

            messaggioRepository.save(messaggio);
            response.put("message", "Message created successfully");
            response.put("status", "200");
            response.put("messaggio", messaggio);
            return ResponseEntity.ok(response);
        }

    }
}


