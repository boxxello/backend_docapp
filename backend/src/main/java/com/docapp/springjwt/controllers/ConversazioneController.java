package com.docapp.springjwt.controllers;

import com.docapp.Utils.ConnPom;

import com.docapp.springjwt.exceptions.ResourceNotFoundException;
import com.docapp.springjwt.models.Conversazione;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.payload.request.ConversazioneRequest;
import com.docapp.springjwt.payload.request.MessaggioRequest;
import com.docapp.springjwt.repository.ConversazioneRepository;
import com.docapp.springjwt.repository.RoleRepository;
import com.docapp.springjwt.repository.UserRepository;
import com.docapp.springjwt.security.jwt.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/conversazioni")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ConversazioneController {

    @Autowired
    private ConversazioneRepository conversazioneRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<Conversazione> getAllConversazioni(@AuthenticationPrincipal UserDetails userDetails) {
        // Get the signed in user
        User currentUser = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));
        System.out.println(currentUser.getId());
        return conversazioneRepository.findAllByStudente1OrStudente2(currentUser);
    }

    @GetMapping("/{conversazioneId}")
    public ResponseEntity<Conversazione> getConversazioneById(@AuthenticationPrincipal UserDetails userDetails,
                                                              @PathVariable Long conversazioneId) {
        // Get the signed in user
        User currentUser = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));
        // Find the conversation by ID
        Optional<Conversazione> optionalConversazione = conversazioneRepository.findByIdAndStudenti1OrStudente2(conversazioneId, currentUser);
        if (optionalConversazione.isPresent()) {
            Conversazione conversazione = optionalConversazione.get();
            return new ResponseEntity<>(conversazione, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/new")
    public Conversazione createConversazione(@AuthenticationPrincipal UserDetails userDetails,
                                             @RequestBody Conversazione conversazione) {
        // Get the signed in user
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //check if the studente2 exists
        System.out.println(conversazione.getStudente2().getUsername());
        User studente2 = userRepository.findByUsername(conversazione.getStudente2().getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User2 not found"));

        System.out.println("Qui arrivo");
        //check if the conversation already exists
        Optional<Conversazione> optionalConversazione = conversazioneRepository.findByStudente1AndStudente2(currentUser, conversazione.getStudente2())
                .or(() -> conversazioneRepository.findByStudente1AndStudente2(conversazione.getStudente2(), currentUser));
        System.out.println("Qui arrivo2");
        if (optionalConversazione.isPresent()) {
            return optionalConversazione.get();
        }
        conversazione.setStudente1(currentUser);
        conversazione.setStudente2(conversazione.getStudente2());
        conversazione.setNomeConversazione(userDetails.getUsername() + "_" + conversazione.getStudente2().getUsername());
        conversazioneRepository.save(conversazione);
        // Save the conversation
        return conversazione;
    }
}
