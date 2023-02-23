package com.docapp.springjwt.controllers;

import com.docapp.springjwt.exceptions.ResourceNotFoundException;
import com.docapp.springjwt.models.Universita;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.payload.response.MessageResponse;
import com.docapp.springjwt.repository.ConversazioneRepository;
import com.docapp.springjwt.repository.UniversitaRepository;
import com.docapp.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/universita")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UniversitaController {
    @Autowired
    private UniversitaRepository universitaRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/get")
    public ResponseEntity<?> getAllUniversita(
            @RequestParam(required = false) String nome
    ) {
        List<Universita> universita_trovate;
        if (nome == null) {
            universita_trovate = universitaRepository.findAll();
        } else {
            universita_trovate = universitaRepository.findAllByNome(nome)
                    .orElse(Collections.emptyList());
        }
        System.out.println(universita_trovate);
        HashMap<String, Object> response = new HashMap<>();

        if (universita_trovate.isEmpty()) {
            //add an empty list to the response
            response.put("universita", Collections.emptyList());
            response.put("message", "Nessuna università trovata");
            return ResponseEntity
                    .ok()
                    .body(response);
        }
        response.put("universita", universita_trovate);
        return ResponseEntity.ok().body(
                response
        );


    }


    @PostMapping("/add")
    public ResponseEntity<?> addUniversita(
            @RequestBody @Valid Universita universita
    ) {
        Universita universita_trovata = universitaRepository.findByNome(universita.getNome())
                .orElse(null);
        if (universita_trovata != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("L'università esiste già"));
        }
        universitaRepository.save(universita);
        return ResponseEntity.ok().body(new MessageResponse("Università aggiunta con successo"));

    }


}
