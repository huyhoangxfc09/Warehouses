package MenuOutput;

import OptionClass.Customer;
import Manager.CustomerManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCustomer {
    public void menu(Scanner scanner){
        CustomerManager customerManager = new CustomerManager();
        customerManager.inputFile(customerManager.pathCustomer);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU CUSTOMER-----");
                    System.out.println("1. Add new customer. ");
                    System.out.println("2. Update customer. ");
                    System.out.println("3. Delete customer. ");
                    System.out.println("4. Search customer by name. ");
                    System.out.println("5. Display all customer. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            Customer customer = customerManager.create(scanner);
                            customerManager.add(customer);
                            customerManager.outputFile(customerManager.pathCustomer);
                            check = false;
                            break;
                        case 2:
                            customerManager.displayAll(customerManager.getListCustomer());
                            customerManager.update(scanner);
                            customerManager.outputFile(customerManager.pathCustomer);
                            check = false;
                            break;
                        case 3:
                            customerManager.displayAll(customerManager.getListCustomer());
                            customerManager.delete(scanner);
                            check=false;
                            break;
                        case 4:
                            customerManager.displayAll(customerManager.getListCustomer());
                            customerManager.searchName(scanner);
                            check=false;
                            break;
                        case 5:
                            customerManager.displayAll(customerManager.getListCustomer());
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
