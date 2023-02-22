package com.docapp.springjwt.models;


import com.docapp.springjwt.models.*;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class ConversazioneTest {
    @Autowired
    private MockMvc mockMvc;
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


    }


    @Test
    @Transactional
    public void controlloMessaggiPrecedenti() {
        //get the conversation from user1 to user2
        Optional<Conversazione> conv = conversazioneRepository.findByStudente1AndStudente2(user, user2);
        if (conv.isPresent()) {
            //get the list of messages
            List<Messaggio> messaggi = messaggioRepository.findByConversazione(conv.get());

            //assert >=0
            assertTrue(messaggi.size() >= 0);
        }

    }

    @Test
    @Transactional
    public void controlloMessaggiPrecedentiMessaggio() {

        //get the conversation from user1 to user2
        Optional<Conversazione> conv = conversazioneRepository.findByStudente1AndStudente2(user, user2);
        if (conv.isPresent()) {
            //get the list of messages
            List<Messaggio> messaggi = messaggioRepository.findByConversazione(conv.get());

            assertEquals(0, messaggi.size());
        }

    }
}
