package com.docapp.Extractors;

import com.docapp.shared_docapp.dao_related.ResultSetExtractor;
import com.docapp.shared_docapp.models.Interazione_Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Interazione_PostExtractor implements ResultSetExtractor<Interazione_Post> {
    @Override
    public Interazione_Post extract(ResultSet rs) throws SQLException {
        Interazione_Post interazionePost = new Interazione_Post();
        interazionePost.setEmail_studente(rs.getString("studente"));
        interazionePost.setId_documento(rs.getInt("documento"));

        return interazionePost;
    }
}
