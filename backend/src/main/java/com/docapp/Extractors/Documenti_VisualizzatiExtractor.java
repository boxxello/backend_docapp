package com.docapp.Extractors;

import com.docapp.dao_related.ResultSetExtractor;

import com.docapp.shared_docapp.models.Documenti_Visualizzati;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Documenti_VisualizzatiExtractor implements ResultSetExtractor<Documenti_Visualizzati> {
    @Override
    public Documenti_Visualizzati extract(ResultSet rs) throws SQLException {
        Documenti_Visualizzati documentiVisualizzati = new Documenti_Visualizzati();
        documentiVisualizzati.setId_documento(rs.getInt("documento"));
        documentiVisualizzati.setEmail_studente(rs.getString("studente"));

        return documentiVisualizzati;
    }
}
