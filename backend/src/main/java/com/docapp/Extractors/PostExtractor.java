package com.docapp.Extractors;

import com.docapp.shared_docapp.dao_related.ResultSetExtractor;
import com.docapp.shared_docapp.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostExtractor implements ResultSetExtractor<Post> {
    @Override
    public Post extract(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId_post(rs.getString("id"));
        post.setTitolo(rs.getString("titolo"));
        post.setTesto(rs.getString("testo"));
        post.setIs_domanda(rs.getBoolean("is_domanda"));

        return post;
    }
}