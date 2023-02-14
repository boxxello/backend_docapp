package com.docapp.springjwt.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.docapp.springjwt.models.ERole;
import com.docapp.springjwt.models.Role;
import com.docapp.springjwt.models.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.docapp.springjwt.payload.request.LoginRequest;
import com.docapp.springjwt.payload.request.SignupRequest;
import com.docapp.springjwt.payload.response.JwtResponse;
import com.docapp.springjwt.payload.response.MessageResponse;
import com.docapp.springjwt.repository.RoleRepository;
import com.docapp.springjwt.repository.UserRepository;
import com.docapp.springjwt.security.jwt.JwtUtils;
import com.docapp.springjwt.security.services.UserDetailsImpl;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping("/api")
public class EndpointController {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/endpoints")
    public String getEndPointsInView() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                this.requestMappingHandlerMapping.getHandlerMethods();
        List<String> endpoints = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();

            if (mapping.getPatternsCondition() == null) {
                mapping.getPathPatternsCondition().getPatternValues().forEach(pattern -> {
                    System.out.println(
                            method.getBeanType().getName() + "#" + method.getMethod().getName() +
                                    " <-- " + pattern);
                    endpoints.add(pattern);
                });
            } else {
                for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                    System.out.println(
                            method.getBeanType().getName() + "#" + method.getMethod().getName() +
                                    " <-- " + urlPattern);
                    endpoints.add(urlPattern);


                }
            }

        }
        return new Gson().toJson(endpoints);
    }
}
