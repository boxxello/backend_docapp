package com.docapp.springjwt.controllers;

import com.docapp.DAO.CaricamentoDAO;
import com.docapp.Utils.ConnPom;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/caricamento")
public class CaricamentoController {
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String allAccess() {

        CaricamentoDAO dao = new CaricamentoDAO(ConnPom.getDatasource());
        try{
            return dao.doRetrieveAll().toString();
        } catch (SQLException throwables) {
           return "errore";
        }


    }
}