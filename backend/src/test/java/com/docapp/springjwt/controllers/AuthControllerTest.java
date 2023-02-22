package com.docapp.springjwt.controllers;

import com.docapp.springjwt.models.ERole;
import com.docapp.springjwt.models.Role;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.payload.request.LoginRequest;
import com.docapp.springjwt.payload.response.JwtResponse;
import com.docapp.springjwt.repository.RoleRepository;
import com.docapp.springjwt.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;


    private Validator validator;

    private User user;


    //ci prepariamo il nostro utente per i test
    @BeforeEach
    @Transactional
    public void setUp() {
        user = new User();
        user.setUsername("test2");

        user.setPassword(encoder.encode("test"));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //unit test
    //TC_1.1_1 ME1
    @Test
    @Transactional
    public void testRegistrazioneBad() {
        //thanks to @Email decorator in the user model we are able to check without
        //any explictly stated regex expressions for email

        user.setEmail("mma");

        //check if userRepository.save(user); throws an exception
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

    }

    //unit test
    //TC_1.1_3 ME2
    @Transactional
    @Test
    public void testRegistrazioneGoodEmail() throws Exception {
        user.setEmail("toninodragonball@gmail.com");
        userRepository.save(user);
        System.out.println(user.getId());


    }

    //integration test
    //TC_1.1_3 MP2
    @Transactional
    @Test
    public void testLogin() throws Exception {
        user.setEmail("toninodragonball@gmail.com");
        userRepository.save(user);
        assertThat(userRepository.findByUsername("test2")).isNotNull();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test2");
        loginRequest.setPassword("test");
        String requestBody = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        JwtResponse jwtResponse = objectMapper.readValue(responseString, JwtResponse.class);

        assertThat(jwtResponse.getAccessToken()).isNotBlank();
        assertThat(jwtResponse.getUsername()).isEqualTo("test2");
        assertThat(jwtResponse.getEmail()).isEqualTo("toninodragonball@gmail.com");
        assertThat(jwtResponse.getRoles()).contains(String.valueOf(ERole.ROLE_USER));
    }


    //integration test
    //TC_1.1_2 MP1
    @Transactional
    @Test
    public void testLoginBad() throws Exception {
        user.setPassword(encoder.encode("passwordSbagliata"));
        user.setEmail("toninodragonball@gmail.com");
        userRepository.save(user);
        assertThat(userRepository.findByUsername("test2")).isNotNull();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test2");
        loginRequest.setPassword("test");
        String requestBody = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        JwtResponse jwtResponse = objectMapper.readValue(responseString, JwtResponse.class);

        assertThat(jwtResponse.getAccessToken()).isBlank();
        assertThat(jwtResponse.getUsername()).isBlank();
    }




}
