package com.docapp.DAO;

import com.docapp.shared_docapp.Extractors.MessaggioExtractor;
import com.docapp.shared_docapp.dao_related.DAO;
import com.docapp.shared_docapp.dao_related.GenericDAO;
import com.docapp.shared_docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Messaggio;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MessaggioDAO  extends SQLDAO implements DAO<Messaggio> {
    public MessaggioDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Messaggio> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Messaggio.TABLE_NAME, condition, new MessaggioExtractor(), source);
    }

    @Override
    public List<Messaggio> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Messaggio.TABLE_NAME, condition, limit, new MessaggioExtractor(), source);
    }

    @Override
    public List<Messaggio> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Messaggio.TABLE_NAME, condition, limit, offset, new MessaggioExtractor(), source);
    }

    @Override
    public List<Messaggio> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Messaggio.TABLE_NAME, hashmap, new MessaggioExtractor(), source);
    }

    @Override
    public List<Messaggio> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Messaggio.TABLE_NAME, new MessaggioExtractor(), source);
    }

    @Override
    public List<Messaggio> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Messaggio.TABLE_NAME, limit, new MessaggioExtractor(), source);
    }

    @Override
    public List<Messaggio> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Messaggio.TABLE_NAME, limit, offset, new MessaggioExtractor(), source);
    }

    @Override
    public void doSave(Messaggio object) throws SQLException {
        GenericDAO.genericDoSave(Messaggio.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Messaggio> doUpdate(String condition, Messaggio object) throws SQLException {
        return GenericDAO.genericDoUpdate(Messaggio.TABLE_NAME, condition, object.toHashMap(), new MessaggioExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Messaggio.TABLE_NAME, condition, source);
    }

    @Override
    public List<Messaggio> doSaveOrUpdate(Messaggio object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Messaggio.TABLE_NAME, object.toHashMap(), new MessaggioExtractor(), source);
    }
}
