package MenuOutput;

import Manager.*;
import OptionClass.Bill;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuBill {
    public void menu(Scanner scanner){
        ProductManager productManager = new ProductManager();
        CompanyManager companyManager = new CompanyManager(productManager);
        InputManager inputManager = new InputManager(companyManager);
        CustomerManager customerManager = new CustomerManager();
        OutputManager outputManager = new OutputManager(inputManager,customerManager);
        BillManager billManager = new BillManager(outputManager,customerManager);
        productManager.inputFile(productManager.pathProduct);
        companyManager.inputFile(companyManager.pathCompany);
        inputManager.inputFile(inputManager.pathInput);
        customerManager.inputFile(customerManager.pathCustomer);
        outputManager.inputFile(outputManager.pathOutput);
        billManager.inputFile(billManager.pathBill);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU BILL-----");
                    System.out.println("1. Add new bill. ");
                    System.out.println("2. Delete bill. ");
                    System.out.println("3. Search bill by code. ");
                    System.out.println("4. Display all bill. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            Bill bill = billManager.create(scanner);
                            billManager.add(bill);
                            billManager.outputFile(billManager.pathBill);
                            check = false;
                            break;
                        case 2:
                            billManager.displayAll(billManager.getListBill());
                            billManager.delete(scanner);
                            check=false;
                            break;
                        case 3:
                            billManager.displayAll(billManager.getListBill());
                            billManager.searchByCode(scanner);
                            check=false;
                            break;
                        case 4:
                            billManager.displayAll(billManager.getListBill());
                            check=false;
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
