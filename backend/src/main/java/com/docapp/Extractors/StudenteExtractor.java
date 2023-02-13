package com.docapp.Extractors;

import com.docapp.dao_related.ResultSetExtractor;
import com.docapp.shared_docapp.models.Studente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudenteExtractor implements ResultSetExtractor<Studente> {

    @Override
    public Studente extract(ResultSet rs) throws SQLException {
        Studente studente = new Studente();
        studente.setEmail(rs.getString("email"));
        studente.setNickname(rs.getString("nickname"));
        studente.setPassword(rs.getString("password"));
        studente.setIs_admin(rs.getBoolean("is_admin"));
        return studente;
    }
}
