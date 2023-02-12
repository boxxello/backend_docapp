package com.docapp.shared_docapp.DAO;

import com.docapp.shared_docapp.dao_related.DAO;
import com.docapp.shared_docapp.dao_related.GenericDAO;
import com.docapp.shared_docapp.dao_related.SQLDAO;
import com.docapp.shared_docapp.models.Studente;
import com.docapp.shared_docapp.Extractors.StudenteExtractor;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class StudenteDAO extends SQLDAO implements DAO<Studente> {
    public StudenteDAO(DataSource source) {
        super(source);
    }

    @Override
    public List<Studente> doRetrieveByCondition(String condition) throws SQLException {
        return GenericDAO.genericDoRetrieveByCondition(Studente.TABLE_NAME, condition, new StudenteExtractor(), source);
    }

    @Override
    public List<Studente> doRetrieveByConditionWithLimit(String condition, int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimit(Studente.TABLE_NAME, condition, limit, new StudenteExtractor(), source);
    }


    @Override
    public List<Studente> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveByConditionWithLimitAndOffset(Studente.TABLE_NAME, condition, limit, offset, new StudenteExtractor(), source);
    }


    @Override
    public List<Studente> doRetrieveByHashMap(HashMap<String, String> hashmap) throws SQLException {
        return GenericDAO.genericDoRetrievebyHashMap(Studente.TABLE_NAME, hashmap, new StudenteExtractor(), source);
    }

    @Override
    public List<Studente> doRetrieveAll() throws SQLException {
        return GenericDAO.genericDoRetrieveAll(Studente.TABLE_NAME, new StudenteExtractor(), source);
    }

    @Override
    public List<Studente> doRetrieveAllWithLimit(int limit) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimit(Studente.TABLE_NAME, limit, new StudenteExtractor(), source);
    }

    @Override
    public List<Studente> doRetrieveAllWithLimitAndOffset(int limit, int offset) throws SQLException {
        return GenericDAO.genericDoRetrieveAllWithLimitAndOffset(Studente.TABLE_NAME, limit, offset, new StudenteExtractor(), source);
    }

    @Override
    public void doSave(Studente object) throws SQLException {
        GenericDAO.genericDoSave(Studente.TABLE_NAME, object.toHashMap(), source);

    }

    @Override
    public List<Studente> doUpdate(String condition, Studente object) throws SQLException {
        return GenericDAO.genericDoUpdate(Studente.TABLE_NAME, condition, object.toHashMap(), new StudenteExtractor(), source);

    }

    @Override
    public boolean doDelete(String condition) throws SQLException {
        return GenericDAO.genericDoDelete(Studente.TABLE_NAME, condition, source);
    }

    @Override
    public List<Studente> doSaveOrUpdate(Studente object) throws SQLException {
        return GenericDAO.genericDoSaveOrUpdate(Studente.TABLE_NAME, object.toHashMap(), new StudenteExtractor(), source);

    }


}
