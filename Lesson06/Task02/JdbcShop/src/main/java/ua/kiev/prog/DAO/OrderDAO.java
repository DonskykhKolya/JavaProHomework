package ua.kiev.prog.DAO;

import ua.kiev.prog.DTO.Order;
import ua.kiev.prog.DTO.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO extends AbstractDAO<Integer, Order> {

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Orders";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
            "date DATETIME NOT NULL, "+
            "customer_id INT NOT NULL)";
    private static final String CREATE_JUNCTION_TABLE_SQL = "CREATE TABLE Orders_Details (order_id INT NOT NULL, product_id INT NOT NULL, "+
            "PRIMARY KEY (order_id, product_id))";

    public OrderDAO(Connection conn) {
        super(conn, "Orders");
    }

    @Override
    public void init() {
        try (Statement st = getConnection().createStatement()) {
            st.execute(DROP_TABLE_SQL);
            st.execute(CREATE_TABLE_SQL);
            st.execute(CREATE_JUNCTION_TABLE_SQL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int add(Order order) {

        try {
            getConnection().setAutoCommit(false);

            try (PreparedStatement st = getConnection().prepareStatement("INSERT INTO Orders (date, customer_id) VALUES(?, ?)")) {
                st.setDate(1, Date.valueOf(order.getDate()));
                st.setInt(2, order.getCustomer().getId());
                int id = st.executeUpdate();
                order.setId(id);
            }

            for(Product product : order.getProducts()) {
                try (PreparedStatement st2 = getConnection().prepareStatement("INSERT INTO Orders_Details (order_id, product_id) VALUES(?, ?)")) {
                    st2.setInt(1, order.getId());
                    st2.setInt(2, product.getId());
                    st2.executeUpdate();
                }
            }

            getConnection().commit();
            getConnection().setAutoCommit(true);

            return order.getId();

        } catch (SQLException ex) {
            try {
                getConnection().rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(ex);
        }
    }

    public List<Order> getOrderList(int customerId) {

        List<Order> orderList = new ArrayList<>();
        String query = "SELECT o.id, o.date, p.id, p.name, p.description, p.price FROM Orders as o "+
                "LEFT JOIN Orders_Details as od ON o.id = od.order_id "+
                "LEFT JOIN Products as p ON od.product_id = p.id WHERE o.customer_id=?";

        try (PreparedStatement st = getConnection().prepareStatement(query)) {
            st.setInt(1, customerId);

            ResultSet rs = st.executeQuery();
            while(rs.next()) {

            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return orderList;
    }
}
