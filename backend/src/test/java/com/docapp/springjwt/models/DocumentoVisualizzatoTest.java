package com.docapp.springjwt.models;


import com.docapp.springjwt.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentoVisualizzatoTest{

    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;


    @Autowired
    DocumentiVisualizzatiRepository documentiVisualizzatiRepository;

    @Autowired
    DocumentoRepository documentoRepository;
    private User user;

    private DocumentiVisualizzati documentiVisualizzati;
    private Validator validator;
    private Documento documento;
    @Transactional
    @BeforeEach
    public void setUp() {


        user = new User();
        user.setEmail("test1@gmail.com");
        user.setUsername("test1");
        user.setPassword("password");

        //add roles
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);


        userRepository.save(user);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        documento=new Documento();

        documento.setDimensione(1000L);
        documento.setNome("nome");
        documento.setStudente(user);

        documento.setDescrizione("descrizione");
        // Create a new instance of DocumentoVisualizzato
        documento.setPath("C:\\Users\\Daniele\\Desktop\\test\\test.pdf");

        documento.setHash("55e9f259f3cd3be8cada7eba8e7814c9");
        documentoRepository.save(documento);

         documentiVisualizzati= new DocumentiVisualizzati(
                user, documento
        );
        documentiVisualizzatiRepository.save(documentiVisualizzati);
        // Save the instance to the repository

        //get all the documents and print them
//        List<DocumentiVisualizzati> documentiVisualizzatis = documentiVisualizzatiRepository.findAll();
//        for (DocumentiVisualizzati documentiVisualizzati : documentiVisualizzatis) {
//            System.out.println(documentiVisualizzati.getDocumento().getNome());
//        }




    }



    //test TC_2.2.1 MDV_1
    @Test
    @Transactional
    public void TC_2_2_1(){
        //check if it has been seen
        List<DocumentiVisualizzati> documenti = documentiVisualizzatiRepository.findByStudente(user);
        assertNotNull(documenti);
        assertFalse(documenti.isEmpty());
        assertTrue(documenti.stream().anyMatch(dv -> dv.getDocumento().equals(documento)));
    }

    //test TC_2.2.2 MDV_2
    @Test
    @Transactional
    public void TC_2_2_2(){
        //check if it has been seen

        List<DocumentiVisualizzati> documenti = documentiVisualizzatiRepository.findByStudente(user);
        assertNotNull(documenti);
        assertFalse(documenti.isEmpty());
        assertTrue(documenti.stream().anyMatch(dv -> dv.getDocumento().equals(documento)));

        user.removeDocumentoVisualizzato(documento);
        documentiVisualizzatiRepository.delete(documentiVisualizzati);
        //check if the reference has been removed
        documenti = documentiVisualizzatiRepository.findByStudente(user);
        //check if it's the same document
        assertFalse(documenti.stream().anyMatch(dv -> dv.getDocumento().equals(documento)));

    }



}
