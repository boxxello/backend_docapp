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
public class DocumentoTest{

    @Autowired
    ConversazioneRepository conversazioneRepository;

    @Autowired
    MessaggioRepository messaggioRepository;
    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    private User user;

    private Messaggio messaggio;
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



    }




    @Test
    @Transactional
    public void TC_5_1_1(){
       //create a string with 256 characters
        int n = 71;
        String str = "a".repeat(n);
        documento.setNome(str);

        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        assertFalse(violations.isEmpty());
    }
    @Test
    @Transactional
    public void TC_5_1_2(){
        //create a string with 256 characters
        int n = 70;
        String str = "a".repeat(n);
        documento.setNome(str);

        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        assertTrue(violations.isEmpty());
    }

    @Test
    @Transactional
    public void TC_5_1_3(){
        documento.setNome("titolo");
        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        assertTrue(violations.isEmpty());
    }
    @Test
    @Transactional
    public void TC_5_1_4(){
        documento.setDescrizione("*;aaaa");
        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        assertFalse(violations.isEmpty());

    }
    @Test
    @Transactional
    public void TC_5_1_5(){
        int n = 651;
        String str = "a".repeat(n);

        documento.setDescrizione(str);
        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        assertFalse(violations.isEmpty());
    }
    @Test
    @Transactional
    public void TC_5_1_6(){
        int n = 650;
        String str = "a".repeat(n);

        documento.setDescrizione(str);
        Set<ConstraintViolation<Documento>> violations = validator.validate(documento);
        assertTrue(violations.isEmpty());
    }


}
