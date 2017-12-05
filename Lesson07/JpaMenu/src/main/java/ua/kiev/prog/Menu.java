package ua.kiev.prog;

import ua.kiev.prog.dao.MenuItemDAO;
import ua.kiev.prog.dao.MenuItemDAOImpl;
import ua.kiev.prog.entity.MenuItem;

import java.util.List;
import java.util.Scanner;


public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private MenuItemDAO dao = new MenuItemDAOImpl();

    public void open() {

        while(true) {
            System.out.println("1 - add new menu item");
            System.out.println("2 - select items by price");
            System.out.println("3 - select items with discount");
            System.out.println("4 - select items by summary weight");
            System.out.println("->");
            String s = scanner.nextLine();

            switch(s) {
                case "1":
                    addItem();
                    break;
                case "2":
                    getByPrice();
                    break;
                case "3":
                    getWithDiscount();
                    break;
                case "4":
                    getBySumWeight();
                    break;
                default:
                    close();
                    return;
            }
        }
    }

    private void addItem() {

        System.out.println("Enter the name of the dish: ");
        String name = scanner.nextLine();
        System.out.println("Enter the price of the dish: ");
        String priceStr = scanner.nextLine();
        double price = Double.parseDouble(priceStr);
        System.out.println("Enter the weight of the dish: ");
        String weightStr = scanner.nextLine();
        int weight = Integer.parseInt(weightStr);
        System.out.println("Is there a discount? (+/-)");
        String discountStr = scanner.nextLine();
        boolean discount = discountStr.equals("+");

        MenuItem newItem = new MenuItem(name, price, weight, discount);
        boolean success = dao.add(newItem);
        if(success) {
            System.out.println("The new record was successfully added to the menu.");
        }
        else {
            System.out.println("An error has occurred. Data is not saved.");
        }
    }

    private void getByPrice() {

        System.out.println("Enter the min price: ");
        String minStr = scanner.nextLine();
        double min = Double.parseDouble(minStr);
        System.out.println("Enter the max price: ");
        String maxStr = scanner.nextLine();
        double max = Double.parseDouble(maxStr);

        List<MenuItem> resultList = dao.getByPrice(min, max);
        if(resultList.size() > 0) {
            resultList.forEach(System.out::println);
        }
        else {
            System.out.println("No records found.");
        }
    }

    private void getWithDiscount() {

        List<MenuItem> resultList = dao.getWithDiscount();
        if(resultList.size() > 0) {
            resultList.forEach(System.out::println);
        }
        else {
            System.out.println("No records found.");
        }
    }

    private void getBySumWeight() {

        System.out.println("Enter the max summary weight: ");
        String maxStr = scanner.nextLine();
        int maxWeight = Integer.parseInt(maxStr);

        List<MenuItem> resultList = dao.getByWeight(maxWeight);
        if(resultList.size() > 0) {
            resultList.forEach(System.out::println);
        }
        else {
            System.out.println("No records found.");
        }
    }

    private void close() {

        scanner.close();
        dao.close();
    }
}
