package com.docapp.dao_related;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface  ResultSetExtractor<B> {
    B extract (ResultSet rs) throws SQLException;
}
