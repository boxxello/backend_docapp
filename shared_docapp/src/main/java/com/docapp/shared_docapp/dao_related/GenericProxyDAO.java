package com.docapp.shared_docapp.dao_related;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenericProxyDAO {

    private final static Logger logger = Logger.getLogger(GenericProxyDAO.class.getName());
    private GenericProxyDAO() {
    }

    public static <T> List<T> doRetrieveByCondition(String condition, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveByCondition(condition);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;


    }


    public static <T> List<T> doRetrieveByConditionWithLimit(String condition, int limit, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveByConditionWithLimit(condition, limit);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;
    }

    public static <T> List<T> doRetrieveByConditionWithLimitAndOffset(String condition, int limit, int offset, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveByConditionWithLimitAndOffset(condition, limit, offset);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;
    }


    public static <T> List<T> doRetrieveByHashMap(HashMap<String, String> hashMap, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveByHashMap(hashMap);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;
    }

    public static <T> List<T> doRetrieveAll(DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveAll();
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;
    }

    public static <T> List<T> doRetrieveAllWithLimit(int limit, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveAllWithLimit(limit);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;
    }

    public static <T> List<T> doRetrieveAllWithLimitAndOffset(int limit, int offset, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result = dao.doRetrieveAllWithLimitAndOffset(limit, offset);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;
    }

    //implement doSave
    public static <T> void doSave(T entity, DAO<T> dao, PrintWriter out) throws SQLException {
        dao.doSave(entity);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(entity);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
    }

    //doUpdate
    public static <T> List<T>  doUpdate(String condition, T entity, DAO<T> dao, PrintWriter out) throws SQLException {
        List<T> result =  dao.doUpdate(condition, entity);

        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(result);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return result;

    }

    //doDeletes
    public static <T> boolean doDelete(String condition, T entity, DAO<T> dao, PrintWriter out) throws SQLException {
        dao.doDelete(condition);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(entity);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
        return true;
    }

    //doSaveOrUpdate
    public static <T> void doSaveOrUpdate(T entity, DAO<T> dao, PrintWriter out) throws SQLException {
        dao.doSaveOrUpdate(entity);
        Gson builder = new GsonBuilder().setPrettyPrinting().create();

        String json = builder.toJson(entity);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("result", json);
        //send response
        out.println(builder.toJson(map));
    }

}
