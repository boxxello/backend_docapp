package com.docapp.shared_docapp.DAO;

import com.docapp.shared_docapp.Extractors.Documenti_VisualizzatiExtractor;
import com.docapp.shared_docapp.dao_related.DAO;
import com.docapp.shared_docapp.dao_related.GenericDAO;
import com.docapp.shared_docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Caricamento;
import com.docapp.shared_docapp.models.Documenti_Visualizzati;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Documenti_VisualizzatiDAO extends SQLDAO implements DAO<Documenti_Visualizzati> {
    public Documenti_VisualizzatiDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Documenti_Visualizzati.TABLE_NAME, condition, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Documenti_Visualizzati.TABLE_NAME, condition, limit, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Documenti_Visualizzati.TABLE_NAME, condition, limit, offset, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Documenti_Visualizzati.TABLE_NAME, hashmap, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Documenti_Visualizzati.TABLE_NAME, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Documenti_Visualizzati.TABLE_NAME, limit, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Documenti_Visualizzati.TABLE_NAME, limit, offset, new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public void doSave(Documenti_Visualizzati object) throws SQLException {
         GenericDAO.genericDoSave(Documenti_Visualizzati.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Documenti_Visualizzati> doUpdate(String condition, Documenti_Visualizzati object) throws SQLException {
        return GenericDAO.genericDoUpdate(Documenti_Visualizzati.TABLE_NAME, condition, object.toHashMap(), new Documenti_VisualizzatiExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Documenti_Visualizzati.TABLE_NAME, condition, source);
    }

    @Override
    public List<Documenti_Visualizzati> doSaveOrUpdate(Documenti_Visualizzati object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Documenti_Visualizzati.TABLE_NAME, object.toHashMap(), new Documenti_VisualizzatiExtractor(), source);
    }
}
