package com.docapp.DAO;

import com.docapp.Extractors.FeedbackExtractor;
import com.docapp.dao_related.GenericDAO;
import com.docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Feedback;
import com.docapp.shared_docapp.shared_DAO.DAO;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class FeedbackDAO extends SQLDAO implements DAO<Feedback> {


    public FeedbackDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Feedback> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Feedback.TABLE_NAME, condition, new FeedbackExtractor(), source);
    }

    @Override
    public List<Feedback> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Feedback.TABLE_NAME, condition, limit, new FeedbackExtractor(), source);
    }

    @Override
    public List<Feedback> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Feedback.TABLE_NAME, condition, limit, offset, new FeedbackExtractor(), source);
    }

    @Override
    public List<Feedback> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Feedback.TABLE_NAME, hashmap, new FeedbackExtractor(), source);
    }

    @Override
    public List<Feedback> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Feedback.TABLE_NAME, new FeedbackExtractor(), source);
    }

    @Override
    public List<Feedback> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Feedback.TABLE_NAME, limit, new FeedbackExtractor(), source);
    }

    @Override
    public List<Feedback> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Feedback.TABLE_NAME, limit, offset, new FeedbackExtractor(), source);
    }

    @Override
    public void doSave(Feedback object) throws SQLException {
        GenericDAO.genericDoSave(Feedback.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Feedback> doUpdate(String condition, Feedback object) throws SQLException {
        return GenericDAO.genericDoUpdate(Feedback.TABLE_NAME, condition, object.toHashMap(), new FeedbackExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Feedback.TABLE_NAME, condition, source);
    }

    @Override
    public List<Feedback> doSaveOrUpdate(Feedback object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Feedback.TABLE_NAME, object.toHashMap(), new FeedbackExtractor(), source);
    }
}
