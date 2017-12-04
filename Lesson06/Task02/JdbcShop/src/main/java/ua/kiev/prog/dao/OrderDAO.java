package ua.kiev.prog.dao;

import ua.kiev.prog.dto.Order;
import ua.kiev.prog.dto.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO extends AbstractDAO<Integer, Order> {

    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS Orders";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
            "date DATETIME NOT NULL, "+
            "customer_id INT NOT NULL)";
    private static final String DROP_JUNCTION_TABLE_SQL = "DROP TABLE IF EXISTS Orders_Details";
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
            st.execute(DROP_JUNCTION_TABLE_SQL);
            st.execute(CREATE_JUNCTION_TABLE_SQL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int add(Order order) {

        int result = 0;

        try {
            getConnection().setAutoCommit(false);

            try (PreparedStatement st = getConnection().prepareStatement("INSERT INTO Orders (date, customer_id) VALUES(?, ?)")) {
                st.setDate(1, Date.valueOf(order.getDate()));
                st.setInt(2, order.getCustomer().getId());
                result = st.executeUpdate();
            }

            try (Statement st = getConnection().createStatement();
                 ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()")) {
                if(rs.next()) {
                    order.setId(rs.getInt(1));
                }
            }

            for(Product product : order.getProducts()) {
                try (PreparedStatement st = getConnection().prepareStatement("INSERT INTO Orders_Details (order_id, product_id) VALUES(?, ?)")) {
                    st.setInt(1, order.getId());
                    st.setInt(2, product.getId());
                    st.executeUpdate();
                }
            }

            getConnection().commit();
            getConnection().setAutoCommit(true);

        } catch (SQLException ex) {
            try {
                getConnection().rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(ex);
        }

        return result;
    }

    public List<Order> getOrderList(int customerId) {

        List<Order> orderList = new ArrayList<>();
        String query = "SELECT o.id, o.date, p.id, p.name, p.description, p.price FROM Orders as o "+
                "LEFT JOIN Orders_Details as od ON o.id = od.order_id "+
                "LEFT JOIN Products as p ON od.product_id = p.id WHERE o.customer_id=?";

        try (PreparedStatement st = getConnection().prepareStatement(query)) {
            st.setInt(1, customerId);

            int curId = 0;
            Order curOrder = null;

            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                if(id != curId) {
                    curOrder = new Order();
                    curOrder.setId(id);
                    curOrder.setDate(rs.getDate(2).toLocalDate());
                    curId = id;
                    orderList.add(curOrder);
                }
                Product curProduct = new Product();
                curProduct.setId(rs.getInt(3));
                curProduct.setName(rs.getString(4));
                curProduct.setDescription(rs.getString(5));
                curProduct.setPrice(rs.getDouble(6));
                curOrder.addProduct(curProduct);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return orderList;
    }

    @Override
    public int change(Order order) {
        System.out.println("Method not supported.");
        return 0;
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Method not supported.");
    }

    @Override
    public List<Order> getAll(Class<Order> cls) {
        System.out.println("Method not supported.");
        return null;
    }
}
