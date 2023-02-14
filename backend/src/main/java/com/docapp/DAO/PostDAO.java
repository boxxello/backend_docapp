package com.docapp.DAO;

import com.docapp.Extractors.PostExtractor;
import com.docapp.dao_related.GenericDAO;
import com.docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Post;
import com.docapp.shared_docapp.shared_DAO.DAO;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PostDAO  extends SQLDAO implements DAO<Post> {
    public PostDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Post> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Post.TABLE_NAME, condition, new PostExtractor(), source);
    }

    @Override
    public List<Post> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Post.TABLE_NAME, condition, limit, new PostExtractor(), source);
    }

    @Override
    public List<Post> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Post.TABLE_NAME, condition, limit, offset, new PostExtractor(), source);
    }

    @Override
    public List<Post> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Post.TABLE_NAME, hashmap, new PostExtractor(), source);
    }

    @Override
    public List<Post> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Post.TABLE_NAME, new PostExtractor(), source);
    }

    @Override
    public List<Post> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Post.TABLE_NAME, limit, new PostExtractor(), source);
    }

    @Override
    public List<Post> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Post.TABLE_NAME, limit, offset, new PostExtractor(), source);
    }

    @Override
    public void doSave(Post object) throws SQLException {
        GenericDAO.genericDoSave(Post.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Post> doUpdate(String condition, Post object) throws SQLException {
        return GenericDAO.genericDoUpdate(Post.TABLE_NAME, condition, object.toHashMap(), new PostExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Post.TABLE_NAME, condition, source);
    }

    @Override
    public List<Post> doSaveOrUpdate(Post object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Post.TABLE_NAME, object.toHashMap(), new PostExtractor(), source);
    }
}
