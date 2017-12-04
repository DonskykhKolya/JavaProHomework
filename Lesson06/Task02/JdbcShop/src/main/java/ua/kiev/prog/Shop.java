package ua.kiev.prog;

import ua.kiev.prog.dao.CustomerDAO;
import ua.kiev.prog.dao.OrderDAO;
import ua.kiev.prog.dao.ProductDAO;
import ua.kiev.prog.dto.Customer;
import ua.kiev.prog.dto.Order;
import ua.kiev.prog.dto.Product;
import ua.kiev.prog.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Shop {

    private Customer currCustomer;

    private Connection conn;
    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    private Scanner sc = new Scanner(System.in);

    public Shop() {
        initDAO();
    }

    private void initDAO() {

        conn = ConnectionFactory.getConnection();
        customerDAO = new CustomerDAO(conn);
        customerDAO.init();
        productDAO = new ProductDAO(conn);
        productDAO.init();
        orderDAO = new OrderDAO(conn);
        orderDAO.init();
    }

    public void showLoginMenu() {

        System.out.println("1: register");
        System.out.println("2: sign in");
        System.out.print("-> ");

        String s = sc.nextLine();
        switch (s) {
            case "1":
                register();
                break;
            case "2":
                signIn();
                break;
            default:
                close();
                return;
        }
    }

    private void register() {

        System.out.println("Enter your login: ");
        String login = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your address: ");
        String address = sc.nextLine();
        System.out.println("Enter your phone: ");
        String phone = sc.nextLine();

        Customer customer = new Customer(login, password);
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);

        if(!customerDAO.exists(login, password)) {
            int res = customerDAO.add(customer);
            if (res != 0) {
                currCustomer = customer;
                showMainMenu();
            } else {
                System.out.println("Registration failed!");
                showLoginMenu();
            }
        }
        else {
            System.out.println("Customer with login " + login + " already exists.");
        }
    }

    private void signIn() {

        System.out.println("Enter your login: ");
        String login = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        currCustomer = customerDAO.find(login, password);
        if(currCustomer != null) {
            showMainMenu();
        }
        else {
            System.out.println("Incorrect login/password.");
            showLoginMenu();
        }
    }

    private void showMainMenu() {

        while(true) {
            System.out.println("1: add product");
            System.out.println("2: show product list");
            System.out.println("3: add order");
            System.out.println("4: show order list");
            System.out.println("5: show customer info");
            System.out.print("-> ");

            String s = sc.nextLine();
            switch (s) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    showProductList();
                    break;
                case "3":
                    addOrder();
                    break;
                case "4":
                    showOrderList();
                    break;
                case "5":
                    showCustomerInfo();
                    break;
                default:
                    close();
                    return;
            }
        }
    }

    private void addProduct() {

        System.out.println("Enter product name: ");
        String name = sc.nextLine();
        System.out.println("Enter product description: ");
        String desc = sc.nextLine();
        System.out.println("Enter product price: ");
        String priceStr = sc.nextLine();
        double price = Double.parseDouble(priceStr);

        Product product = new Product(name, price);
        product.setDescription(desc);

        int res = productDAO.add(product);
        if(res == 0) {
            System.out.println("Can't add new product.");
        }
    }

    private void showProductList() {
        List<Product> productList = productDAO.getAll(Product.class);
        productList.forEach(System.out::println);
    }

    private void addOrder() {

        System.out.println("Enter 'x' to complete your order.");
        Order order = new Order();
        order.setCustomer(currCustomer);
        order.setDate(LocalDate.now());

        String s = "";
        do {
            System.out.println("Enter product name: ");
            s = sc.nextLine();

            Product product = productDAO.findByName(s);
            if(product != null) {
                order.addProduct(product);
            }
            else {
                System.out.println("Product with name " + s + " does not exist.");
            }
        } while (!s.equals("x"));

        int res = orderDAO.add(order);
        if(res == 0) {
            System.out.println("Can't save your order. Please, try again later.");
        }
    }

    private void showOrderList() {
        List<Order> orders = orderDAO.getOrderList(currCustomer.getId());
        orders.forEach(System.out::println);
    }

    private void showCustomerInfo() {
        System.out.println(currCustomer.toString());
    }

    private void close() {
        sc.close();
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
