package ua.kiev.prog.dao;

import ua.kiev.prog.dto.Customer;

import java.sql.*;


public class CustomerDAO extends AbstractDAO<Integer, Customer> {

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Customers";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE Customers (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
            "login VARCHAR(20) NOT NULL, "+
            "password VARCHAR(20) NOT NULL, "+
            "name VARCHAR(30) NOT NULL, "+
            "address VARCHAR(50) NOT NULL, "+
            "phone VARCHAR(20) NOT NULL)";

    public CustomerDAO(Connection conn) {
        super(conn, "Customers");
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

    public Customer find(String login, String password) {

        Customer customer = null;

        try (PreparedStatement st = getConnection().prepareStatement("SELECT * FROM Customers WHERE login=? AND password=?")) {
            st.setString(1, login);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                customer = new Customer(login, password);
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return customer;
    }

    public boolean exists(String login, String password) {
        Customer customer = find(login, password);
        return customer != null;
    }
}
