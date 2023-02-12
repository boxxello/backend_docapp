package com.docapp.Extractors;

import com.docapp.shared_docapp.dao_related.ResultSetExtractor;
import com.docapp.shared_docapp.models.Conversazione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversazioneExtractor implements ResultSetExtractor<Conversazione> {

    @Override
    public Conversazione extract(ResultSet rs) throws SQLException {
        Conversazione conversazione = new Conversazione();
        conversazione.setId_conversazione(rs.getInt("id_conversazione"));
        conversazione.setEmail_studente(rs.getString("nome_conversazione"));
        conversazione.setNome_conversazione(rs.getString("studente"));

        return conversazione;
    }
}
