package com.docapp.shared_docapp.Extractors;

import com.docapp.shared_docapp.models.Documento;
import com.docapp.shared_docapp.dao_related.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentoExtractor implements ResultSetExtractor<Documento> {
    @Override
    public Documento extract(ResultSet rs) throws SQLException {
        Documento documento = new Documento();
        documento.setId_documento(rs.getInt("id"));
        documento.setNome(rs.getString("nome"));
        documento.setDescrizione(rs.getString("descrizione"));
        documento.setUniversita(rs.getString("universita"));
        documento.setFacolta(rs.getString("facolta"));
        documento.setCorso_di_studio(rs.getString("corso_di_studio"));
        documento.setPath(rs.getString("percorso"));
        documento.setDimensione(rs.getInt("dimensione"));

        return documento;
    }
}
