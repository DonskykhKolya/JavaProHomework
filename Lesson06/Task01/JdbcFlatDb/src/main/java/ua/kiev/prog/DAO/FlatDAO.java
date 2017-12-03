package ua.kiev.prog.DAO;

import ua.kiev.prog.DTO.Flat;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class FlatDAO extends AbstractDAO<Integer, Flat> {

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Flats";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
            "district VARCHAR(20) NOT NULL, "+
            "address VARCHAR(30) NOT NULL, "+
            "area DOUBLE NOT NULL, "+
            "roomCount INT NOT NULL, "+
            "price DOUBLE NOT NULL)";

    public FlatDAO(Connection conn, String table) {
        super(conn, table);
    }

    @Override
    public void init() {
        try (Statement st = getConnection().createStatement()) {
            st.execute(DROP_TABLE_SQL);
            st.execute(CREATE_TABLE_SQL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
