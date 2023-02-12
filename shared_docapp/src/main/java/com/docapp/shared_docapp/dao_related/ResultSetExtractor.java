package com.docapp.shared_docapp.dao_related;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface  ResultSetExtractor<B> {
    B extract (ResultSet rs) throws SQLException;
}
