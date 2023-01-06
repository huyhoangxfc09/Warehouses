package MenuAdmin;

import Manager.*;
import OptionClass.OutputWarehouse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOutput {
    public void mainOutput(Scanner scanner){
        ProductManager productManager = new ProductManager();
        CompanyManager companyManager = new CompanyManager(productManager);
        InputManager inputManager = new InputManager(companyManager);
        CustomerManager customerManager = new CustomerManager();
        OutputManager outputManager = new OutputManager(inputManager,customerManager);
        productManager.inputFile(productManager.pathProduct);
        companyManager.inputFile(companyManager.pathCompany);
        inputManager.inputFile(inputManager.pathInput);
        outputManager.inputFile(outputManager.pathOutput);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU OUTPUT WAREHOUSE-----");
                    System.out.println("1. Add products out of stock. ");
                    System.out.println("2. Update products out of stock. ");
                    System.out.println("3. Remove products from the list of out-of-stocks. ");
                    System.out.println("4. Search products from the list of out-of-stocks by code. ");
                    System.out.println("6. Show list of products in stock . ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            OutputWarehouse outputWarehouse = outputManager.create(scanner);
                            outputManager.add(outputWarehouse);
                            outputManager.outputFile(outputManager.pathOutput);
                            check = false;
                            break;
                        case 2:
                            outputManager.displayAll(outputManager.getListOutput());
                            outputManager.update(scanner);
                            outputManager.outputFile(outputManager.pathOutput);
                            check = false;
                            break;
                        case 3:
                            outputManager.displayAll(outputManager.getListOutput());
                            outputManager.delete(scanner);
                            outputManager.outputFile(outputManager.pathOutput);
                            check = false;
                            break;
                        case 4:
                            outputManager.displayAll(outputManager.getListOutput());
                            outputManager.searchByCode(scanner);
                            check = false;
                            break;
                        case 6:
                            outputManager.displayAll(outputManager.getListOutput());
                            check = false;
                            break;
                    }
                } while (choice != 0);
            } catch (InputMismatchException | NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Re-enter choice.");
            }
        }
    }
}
