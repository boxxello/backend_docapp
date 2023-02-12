package com.docapp.dao_related;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GenericDAO<T> {
    static Logger logger = Logger.getLogger(GenericDAO.class.getName());


    /**
     * Eseguo la query e tramite un generico extractor estraggo i dati dal ResultSet
     * e li salvo in una lista di oggetti di tipo T
     **/

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrieveByCondition(String table, String condition, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).WHERE(condition).toString();

            logger.info("[GENERIC-DO-RETRIEVE-BY-CONDITION] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }


    /**
     * Eseguo il metodo executeUpdate per salvare l'oggetto in database (solo i field
     * contenuti nella Map)
     * e verifico se è andato a buon fine restituendo rows == 1
     **/

    public static boolean genericDoSave(String table, HashMap<String, ?> map, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.INSERT_INTO(table, map).toString();

            logger.info("[GENERIC-DO-SAVE] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                rows = ps.executeUpdate();
            }
        }
        return rows == 1;
    }

    /**
     * Eseguo il metodo executeUpdate per aggiornare l'oggetto in database (solo i field
     * contenuti nella Map)
     * e verifico se è andato a buon fine restituendo rows > 0
     **/

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoUpdate(String table, String condition, Map<String, ?> values, E extractor, DataSource source) throws SQLException {
        //ritorna la lista di oggetti aggiornati
        final List<T> entity = new ArrayList<>();
        try (Connection conn = source.getConnection()) {


            String query = QueryBuilder.UPDATE(table).SET(values).WHERE(condition).toString();
            String query_select_before_update = QueryBuilder.SELECT("*").FROM(table).WHERE(condition).toString();
            logger.info("[GENERIC-DO-UPDATE] " + query);
            logger.info("[GENERIC-DO-UPDATE] " + query_select_before_update);

            try (PreparedStatement ps = conn.prepareStatement(query_select_before_update)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }

            }
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.executeUpdate();
            }

        }
        return entity;

    }

    /**
     * Eseguo il metodo executeUpdate per eliminare l'oggetto in database
     * e verifico se è andato a buon fine restituendo rows > 0
     * prima di eseguire la query disabilito i check sulla foreign key
     * e li riabilito dopo aver cancellato
     * nota: i vincoli dopo la query sono ancora rispettati e non vengono create
     * condizioni di inconsistenza dei dati
     **/

    public static boolean genericDoDelete(String table, String  condition, DataSource source) throws SQLException {

        int rows;
        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.DELETE_FROM(table).WHERE(condition).toString();

            logger.info("[GENERIC-DO-DELETE] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.execute("SET FOREIGN_KEY_CHECKS = 0;");
                rows = ps.executeUpdate();
                ps.execute("SET FOREIGN_KEY_CHECKS = 1;");
            }
        }
        return rows > 0;
    }

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrieveAll(String table, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).toString();

            logger.info("[GENERIC-DO-RETRIEVE-ALL] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrieveAllWithLimit(String table, int limit, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).LIMIT(limit).toString();

            logger.info("[GENERIC-DO-RETRIEVE-ALL-WITH-LIMIT] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrieveAllWithLimitAndOffset(String table, int limit, int offset, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).LIMIT(offset, limit).toString();

            logger.info("[GENERIC-DO-RETRIEVE-ALL-WITH-LIMIT-AND-OFFSET] " + query);
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }


    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrievebyHashMap(String table, HashMap<String, String> hashmap, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {
            QueryBuilder query_builder = QueryBuilder.SELECT("*").FROM(table);
            for (Map.Entry<String, String> entry : hashmap.entrySet()) {
                query_builder.WHERE(entry.getKey() + " = " + entry.getValue());
            }
            String query = query_builder.toString();

            logger.info("[GENERIC-DO-RETRIEVE-BY-KEY] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrieveByConditionWithLimit(String table, String condition, int limit, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).WHERE(condition).LIMIT(limit).toString();

            logger.info("[GENERIC-DO-RETRIEVE-BY-CONDITION-WITH-LIMIT] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoRetrieveByConditionWithLimitAndOffset(String table, String condition, int limit, int offset, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query = QueryBuilder.SELECT("*").FROM(table).WHERE(condition).LIMIT(offset, limit).toString();

            logger.info("[GENERIC-DO-RETRIEVE-BY-CONDITION-WITH-LIMIT-AND-OFFSET] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }
            }
        }
        return entity;
    }
    //doSaveOrUpdate

    public static <E extends ResultSetExtractor<T>, T> List<T> genericDoSaveOrUpdate(String table, HashMap<String, ?> object, E extractor, DataSource source) throws SQLException {

        final List<T> entity = new ArrayList<>();

        try (Connection conn = source.getConnection()) {

            String query_select_before_update = QueryBuilder.SELECT("*").FROM(table).WHERE("id = " + object.get("id")).toString();
            String query = QueryBuilder.UPDATE(table).SET(object).WHERE("id = " + object.get("id")).toString();

            logger.info("[GENERIC-DO-SAVE-OR-UPDATE] " + query);

            try (PreparedStatement ps = conn.prepareStatement(query_select_before_update)) {
                ResultSet set = ps.executeQuery();

                while (set.next()) {
                    entity.add(extractor.extract(set));
                }

            }
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.executeUpdate();
            }

        }
        return entity;

    }
}
