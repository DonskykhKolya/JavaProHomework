package ua.kiev.prog.DAO;

import ua.kiev.prog.DTO.Product;

import java.sql.*;


public class ProductDAO extends AbstractDAO<Integer, Product> {

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Products";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE Products (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
            "name VARCHAR(20) NOT NULL, "+
            "description VARCHAR(100) NULL, "+
            "price DOUBLE NOT NULL)";

    public ProductDAO(Connection conn) {
        super(conn, "Products");
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

    public Product findByName(String name) {

        Product product = null;

        try (PreparedStatement st = getConnection().prepareStatement("SELECT * FROM Products WHERE name=?")) {
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(name);
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return product;
    }
}
