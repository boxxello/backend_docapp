package com.docapp.springjwt.controllers;


import com.docapp.springjwt.models.CorsoDiStudio;
import com.docapp.springjwt.models.Universita;
import com.docapp.springjwt.repository.CorsoDiStudioRepository;
import com.docapp.springjwt.repository.UniversitaRepository;
import com.docapp.springjwt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/corsidistudio")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class CorsiDiStudioController {

    private static final Logger logger = LoggerFactory.getLogger(CorsiDiStudioController.class);
    @Autowired
    private UniversitaRepository universitaRepository;

    @Autowired
    private CorsoDiStudioRepository corsoDiStudioRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getByUniversita")
    public ResponseEntity<?> getAllCorsiDiStudioPUniversita(
            @RequestParam String nome,
            @RequestParam(required = false) Long id) {
        HashMap<String, Object> response = new HashMap<>();
        Universita universita_trovata;
        List<CorsoDiStudio> corsi;
        if (id == null) {
            logger.info("Falling back to nome");
            universita_trovata = universitaRepository.findByNome(nome)
                    .orElse(null);


        } else {
            universita_trovata = universitaRepository.findById(id)
                    .orElse(null);
        }
        if (universita_trovata == null) {
            response.put("message", "Nessuna università trovata");
            return ResponseEntity.badRequest()
                    .body(response);
        }
        corsi = corsoDiStudioRepository.findAllByUniversita(universita_trovata);

        response.put("corsi", corsi);
        return ResponseEntity.ok().body(
                response
        );


    }

    @PostMapping("/add")
    public ResponseEntity<?> addCorsoDiStudio(@RequestParam("id") Long id, CorsoDiStudio corsoDiStudio) {

        System.out.println(id);
        HashMap<String, Object> response = new HashMap<>();
        Universita universita_trovata = universitaRepository.findById(id)
                .orElse(null);
        if (universita_trovata == null) {
            return
                    ResponseEntity.badRequest()
                            .body("Nessuna università trovata");
        }

        corsoDiStudio.setUniversita(universita_trovata);


        corsoDiStudioRepository.save(corsoDiStudio);
        System.out.println("MANNAGGIA A TONINO");
        System.out.println(corsoDiStudio.getNome());

        response.put("corso", corsoDiStudio);
        response.put("message", "Corso di studio aggiunto");
        System.out.println(response);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
