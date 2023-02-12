package com.docapp.shared_docapp.DAO;

import com.docapp.shared_docapp.Extractors.Interazione_PostExtractor;
import com.docapp.shared_docapp.dao_related.DAO;
import com.docapp.shared_docapp.dao_related.GenericDAO;
import com.docapp.shared_docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Interazione_Post;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Interazione_PostDAO extends SQLDAO implements DAO<Interazione_Post> {
    public Interazione_PostDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Interazione_Post> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Interazione_Post.TABLE_NAME, condition, new Interazione_PostExtractor(), source);
    }

    @Override
    public List<Interazione_Post> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Interazione_Post.TABLE_NAME, condition, limit, new Interazione_PostExtractor(), source);
    }

    @Override
    public List<Interazione_Post> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Interazione_Post.TABLE_NAME, condition, limit, offset, new Interazione_PostExtractor(), source);
    }

    @Override
    public List<Interazione_Post> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Interazione_Post.TABLE_NAME, hashmap, new Interazione_PostExtractor(), source);
    }

    @Override
    public List<Interazione_Post> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Interazione_Post.TABLE_NAME, new Interazione_PostExtractor(), source);
    }

    @Override
    public List<Interazione_Post> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Interazione_Post.TABLE_NAME, limit, new Interazione_PostExtractor(), source);
    }

    @Override
    public List<Interazione_Post> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Interazione_Post.TABLE_NAME, limit, offset, new Interazione_PostExtractor(), source);
    }

    @Override
    public void doSave(Interazione_Post object) throws SQLException {
        GenericDAO.genericDoSave(Interazione_Post.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Interazione_Post> doUpdate(String condition, Interazione_Post object) throws SQLException {
        return GenericDAO.genericDoUpdate(Interazione_Post.TABLE_NAME, condition, object.toHashMap(), new Interazione_PostExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Interazione_Post.TABLE_NAME, condition, source);
    }

    @Override
    public List<Interazione_Post> doSaveOrUpdate(Interazione_Post object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Interazione_Post.TABLE_NAME, object.toHashMap(), new Interazione_PostExtractor(), source);
    }
}
