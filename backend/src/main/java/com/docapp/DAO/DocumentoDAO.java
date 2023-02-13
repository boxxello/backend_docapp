package com.docapp.DAO;

import com.docapp.Extractors.DocumentoExtractor;
import com.docapp.dao_related.DAO;
import com.docapp.dao_related.GenericDAO;
import com.docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Documento;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DocumentoDAO  extends SQLDAO implements DAO<Documento> {
    public DocumentoDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Documento> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Documento.TABLE_NAME, condition, new DocumentoExtractor(), source);
    }

    @Override
    public List<Documento> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Documento.TABLE_NAME, condition, limit, new DocumentoExtractor(), source);
    }

    @Override
    public List<Documento> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Documento.TABLE_NAME, condition, limit, offset, new DocumentoExtractor(), source);
    }

    @Override
    public List<Documento> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Documento.TABLE_NAME, hashmap, new DocumentoExtractor(), source);
    }

    @Override
    public List<Documento> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Documento.TABLE_NAME, new DocumentoExtractor(), source);
    }

    @Override
    public List<Documento> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Documento.TABLE_NAME, limit, new DocumentoExtractor(), source);
    }

    @Override
    public List<Documento> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Documento.TABLE_NAME, limit, offset, new DocumentoExtractor(), source);
    }

    @Override
    public void doSave(Documento object) throws SQLException {
        GenericDAO.genericDoSave(Documento.TABLE_NAME, object.toHashMap(), source);
    }

    @Override
    public List<Documento> doUpdate(String condition, Documento object) throws SQLException {
        return GenericDAO.genericDoUpdate(Documento.TABLE_NAME, condition, object.toHashMap(), new DocumentoExtractor(), source);
    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Documento.TABLE_NAME, condition, source);
    }

    @Override
    public List<Documento> doSaveOrUpdate(Documento object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Documento.TABLE_NAME, object.toHashMap(), new DocumentoExtractor(), source);
    }
}
