package MenuAdmin;

import Manager.ProductManager;
import OptionClass.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuProduct {
    public void  mainProduct(Scanner scanner){
        ProductManager productManager = new ProductManager();
        productManager.inputFile(productManager.pathProduct);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU PRODUCT-----");
                    System.out.println("1. Add new product. ");
                    System.out.println("2. Update product by number. ");
                    System.out.println("3. Delete product by code. ");
                    System.out.println("4. Search product by name. ");
                    System.out.println("5. Search product by type. ");
                    System.out.println("6. Sort products by value ascending. ");
                    System.out.println("7. Display all product. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            Product product = productManager.create(scanner);
                            productManager.add(product);
                            productManager.outputFile(productManager.pathProduct);
                            check = false;
                            break;
                        case 2:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.update(scanner);
                            productManager.outputFile(productManager.pathProduct);
                            check = false;
                            break;
                        case 3:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.delete(scanner);
                            productManager.outputFile(productManager.pathProduct);
                            check = false;
                            break;
                        case 4:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.searchName(scanner);
                            check = false;
                            break;
                        case 5:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.searchType(scanner);
                            check = false;
                            break;
                        case 6:
                            productManager.sortPrice();
                            check = false;
                            break;
                        case 7:
                            productManager.displayAll(productManager.getListProduct());
                            check = false;
                            break;
                        case 0:
                            check = false;
                    }
                } while (choice != 0);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter choice.");
            }
        }

    }
}
