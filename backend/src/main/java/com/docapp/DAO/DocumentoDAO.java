package com.docapp.DAO;

import com.docapp.shared_docapp.Extractors.CaricamentoExtractor;
import com.docapp.shared_docapp.dao_related.DAO;
import com.docapp.shared_docapp.dao_related.GenericDAO;
import com.docapp.shared_docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Caricamento;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DocumentoDAO  extends SQLDAO implements DAO<Caricamento> {
    public DocumentoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Caricamento> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Caricamento.TABLE_NAME, condition, new CaricamentoExtractor(), source);
    }

    @Override
    public List<Caricamento> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Caricamento.TABLE_NAME, condition, limit, new CaricamentoExtractor(), source);
    }

    @Override
    public List<Caricamento> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Caricamento.TABLE_NAME, condition, limit, offset, new CaricamentoExtractor(), source);
    }

    @Override
    public List<Caricamento> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Caricamento.TABLE_NAME, hashmap, new CaricamentoExtractor(), source);
    }

    @Override
    public List<Caricamento> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Caricamento.TABLE_NAME, new CaricamentoExtractor(), source);
    }

    @Override
    public List<Caricamento> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Caricamento.TABLE_NAME, limit, new CaricamentoExtractor(), source);
    }

    @Override
    public List<Caricamento> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Caricamento.TABLE_NAME, limit, offset, new CaricamentoExtractor(), source);
    }

    @Override
    public void doSave(Caricamento object) throws SQLException {
        GenericDAO.genericDoSave(Caricamento.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Caricamento> doUpdate(String condition, Caricamento object) throws SQLException {
        return GenericDAO.genericDoUpdate(Caricamento.TABLE_NAME, condition, object.toHashMap(), new CaricamentoExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Caricamento.TABLE_NAME, condition, source);
    }

    @Override
    public List<Caricamento> doSaveOrUpdate(Caricamento object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Caricamento.TABLE_NAME, object.toHashMap(), new CaricamentoExtractor(), source);
    }
}
