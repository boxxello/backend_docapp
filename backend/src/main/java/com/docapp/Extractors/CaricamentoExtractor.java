package com.docapp.Extractors;

import com.docapp.shared_docapp.dao_related.ResultSetExtractor;
import com.docapp.shared_docapp.models.Caricamento;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CaricamentoExtractor implements ResultSetExtractor<Caricamento> {

    @Override
    public Caricamento extract(ResultSet rs) throws SQLException {
        Caricamento caricamento = new Caricamento();
        caricamento.setId(rs.getInt("id"));
        caricamento.setEmail(rs.getString("email"));

        return caricamento;
    }
}
