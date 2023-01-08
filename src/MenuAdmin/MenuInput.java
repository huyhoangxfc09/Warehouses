package MenuAdmin;

import Manager.CompanyManager;
import Manager.InputManager;
import Manager.ProductManager;
import OptionClass.InputWarehouse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInput {
    public void mainInput(Scanner scanner){
        ProductManager productManager = new ProductManager();
        CompanyManager companyManager = new CompanyManager(productManager);
        InputManager inputManager = new InputManager(companyManager);
        productManager.inputFile(productManager.pathProduct);
        companyManager.inputFile(companyManager.pathCompany);
        inputManager.inputFile(inputManager.pathInput);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU INPUT WAREHOUSE-----");
                    System.out.println("1. Add new products to stock. ");
                    System.out.println("2. Update products in stock. ");
                    System.out.println("3. Delete product in stock by code. ");
                    System.out.println("4. Search product in stock by code. ");
                    System.out.println("5. Search product in stock by type. ");
                    System.out.println("6. Show list of imported products. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            InputWarehouse inputWarehouse = inputManager.create(scanner);
                            inputManager.add(inputWarehouse);
                            inputManager.outputFile(inputManager.pathInput);
                            check = false;
                            break;
                        case 2:
                            inputManager.displayAll(inputManager.getListInput());
                            inputManager.update(scanner);
                            inputManager.outputFile(inputManager.pathInput);
                            check = false;
                            break;
                        case 3:
                            inputManager.displayAll(inputManager.getListInput());
                            inputManager.delete(scanner);
                            inputManager.outputFile(inputManager.pathInput);
                            check = false;
                            break;
                        case 4:
                            inputManager.displayAll(inputManager.getListInput());
                            inputManager.searchByCode(scanner);
                            check = false;
                            break;
                        case 5:
                            inputManager.displayAll(inputManager.getListInput());
                            inputManager.searchType(scanner);
                        case 6:
                            inputManager.displayAll(inputManager.getListInput());
                            check = false;
                            break;
                        case 0:
                            check = false;
                    }
                } while (choice != 0);
            } catch (InputMismatchException | NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Re-enter choice.");
            }
        }
    }
}
