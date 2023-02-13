package com.docapp.DAO;

import com.docapp.Extractors.ConversazioneExtractor;
import com.docapp.dao_related.DAO;
import com.docapp.dao_related.GenericDAO;
import com.docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Conversazione;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ConversazioneDAO extends SQLDAO implements DAO<Conversazione> {

    public ConversazioneDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Conversazione> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Conversazione.TABLE_NAME, condition, new ConversazioneExtractor(), source);
    }

    @Override
    public List<Conversazione> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Conversazione.TABLE_NAME, condition, limit, new ConversazioneExtractor(), source);
    }

    @Override
    public List<Conversazione> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Conversazione.TABLE_NAME, condition, limit, offset, new ConversazioneExtractor(), source);
    }

    @Override
    public List<Conversazione> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Conversazione.TABLE_NAME, hashmap, new ConversazioneExtractor(), source);
    }

    @Override
    public List<Conversazione> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Conversazione.TABLE_NAME, new ConversazioneExtractor(), source);
    }

    @Override
    public List<Conversazione> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Conversazione.TABLE_NAME, limit, new ConversazioneExtractor(), source);
    }

    @Override
    public List<Conversazione> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Conversazione.TABLE_NAME, limit, offset, new ConversazioneExtractor(), source);
    }

    @Override
    public void doSave(Conversazione object) throws SQLException {
        GenericDAO.genericDoSave(Conversazione.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Conversazione> doUpdate(String condition, Conversazione object) throws SQLException {
        return GenericDAO.genericDoUpdate(Conversazione.TABLE_NAME, condition, object.toHashMap(), new ConversazioneExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Conversazione.TABLE_NAME, condition, source);
    }

    @Override
    public List<Conversazione> doSaveOrUpdate(Conversazione object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Conversazione.TABLE_NAME, object.toHashMap(), new ConversazioneExtractor(), source);
    }
}
