package com.docapp.Extractors;

import com.docapp.dao_related.ResultSetExtractor;

import com.docapp.shared_docapp.models.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackExtractor implements ResultSetExtractor<Feedback> {
    @Override
    public Feedback extract(ResultSet rs) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("documento"));
        feedback.setEmail(rs.getString("studente"));

        return feedback;
    }
}
