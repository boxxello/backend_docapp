package com.docapp.springjwt.models;


import com.docapp.springjwt.repository.ConversazioneRepository;
import com.docapp.springjwt.repository.MessaggioRepository;
import com.docapp.springjwt.repository.RoleRepository;
import com.docapp.springjwt.repository.UserRepository;
import org.apache.commons.lang3.StringEscapeUtils;
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
import java.nio.charset.Charset;
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

    @BeforeEach
    @Transactional
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

    //TC_4.1_1 LCM1

    @Test
    @Transactional
    public void controlloLunghezzaMessaggio() {
        //get the conversation from user1 to user2
        messaggio.setTesto("");
        Set<ConstraintViolation<Messaggio>> violations = validator.validate(messaggio);
        assertFalse(violations.isEmpty());

    }
    //TC_4.1_2 LCM2
    @Test
    @Transactional
    public void controlloLunghezzaMessaggioPresente() {
        //get the conversation from user1 to user2
        messaggio.setTesto("prova_prova sas sa");
        Set<ConstraintViolation<Messaggio>> violations = validator.validate(messaggio);
        assertTrue(violations.isEmpty());

    }

    //TC_5.1_1 FME1

    @Test
    @Transactional
    public void testUTF8Encoding() throws UnsupportedEncodingException {
        String str = "こんにちは"; // Japanese string encoded in UTF-8
        byte[] bytes = str.getBytes("UTF-8");
        String str2 = new String(bytes, "UTF-8");
        assertEquals(str, str2, "UTF-8 encoding not detected");
    }

    //TC_5.1_2 FME2

    @Test
    @Transactional
    public void testNonUTF8Encoding() {
        String str = "こんにちはÃ"; // Japanese greeting followed by non-UTF-8 character
        Charset utf8Charset = StandardCharsets.UTF_8;
        byte[] encodedBytes = str.getBytes(utf8Charset);
        String decodedStr = new String(encodedBytes, utf8Charset);
        assertEquals(str, decodedStr, "Encoding/Decoding failed");
    }

}
