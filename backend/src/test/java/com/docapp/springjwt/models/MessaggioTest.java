package com.docapp.springjwt.models;


import com.docapp.springjwt.repository.ConversazioneRepository;
import com.docapp.springjwt.repository.MessaggioRepository;
import com.docapp.springjwt.repository.RoleRepository;
import com.docapp.springjwt.repository.UserRepository;
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
public class MessaggioTest {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ConversazioneRepository conversazioneRepository;

    @Autowired
    MessaggioRepository messaggioRepository;
    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    private User user;

    private User user2;

    private Messaggio messaggio;
    private Validator validator;
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

        user2 = new User();
        user2.setUsername("test2");
        user2.setEmail("test2@gmail.com");
        user2.setPassword("password");
        user2.setRoles(roles);

        userRepository.save(user);
        userRepository.save(user2);

        //create a conversation
        Conversazione conv = new Conversazione();
        conv.setNomeConversazione(user.getId() + "_" + user2.getId());
        conv.setStudente1(user);
        conv.setStudente2(user2);
        conversazioneRepository.save(conv);

        messaggio = new Messaggio();
        messaggio.setConversazione(conv);
        messaggio.setTimestamp(LocalDateTime.now());


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    @Transactional
    public void controlloLunghezzaMessaggio() {
        //get the conversation from user1 to user2
        messaggio.setTesto("");
        Set<ConstraintViolation<Messaggio>> violations = validator.validate(messaggio);
        assertFalse(violations.isEmpty());

    }
    @Test
    @Transactional
    public void controlloLunghezzaMessaggioPresente() {
        //get the conversation from user1 to user2
        messaggio.setTesto("prova_prova sas sa");
        Set<ConstraintViolation<Messaggio>> violations = validator.validate(messaggio);
        assertTrue(violations.isEmpty());

    }

    @Test
    @Transactional
    public void checkMessageEncodingUTF8(){
        messaggio.setTesto( "私の");
        byte [] b = messaggio.getTesto().getBytes();
        String newString;
        try {
            newString = new String (b, "UTF-8");
            assertEquals(messaggio.getTesto().length(),newString.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


}
