package com.docapp.shared_docapp.Extractors;

import com.docapp.shared_docapp.models.Messaggio;
import com.docapp.shared_docapp.dao_related.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessaggioExtractor implements ResultSetExtractor<Messaggio> {
    @Override
    public Messaggio extract(ResultSet rs) throws SQLException {
        Messaggio messaggio = new Messaggio();
        messaggio.setId_messaggio(rs.getInt("id"));
        messaggio.setId_conversazione(rs.getInt("conversazione"));
        messaggio.setEmail_studente(rs.getString("studente"));
        messaggio.setTesto(rs.getString("testo"));
        messaggio.setTms(rs.getString("timestamp_msg"));

        return messaggio;
    }
}
