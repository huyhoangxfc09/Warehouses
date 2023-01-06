package MenuAdmin;

import Manager.CompanyManager;
import Manager.ProductManager;
import OptionClass.Company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCompany {
    public void mainCompany(Scanner scanner){
        ProductManager productManager = new ProductManager();
        CompanyManager companyManager = new CompanyManager(productManager);
        productManager.inputFile(productManager.pathProduct);
        companyManager.inputFile(companyManager.pathCompany);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU COMPANY-----");
                    System.out.println("1. Add new company. ");
                    System.out.println("2. Update company by number. ");
                    System.out.println("3. Delete company by number. ");
                    System.out.println("4. Show companies by name. ");
                    System.out.println("5. Search company by code. ");
                    System.out.println("6. Display all company. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            Company company = companyManager.create(scanner);
                            companyManager.add(company);
                            companyManager.outputFile(companyManager.pathCompany);
                            check = false;
                            break;
                        case 2:
                            companyManager.displayAll(companyManager.getCompanyManager());
                            companyManager.update(scanner);
                            companyManager.outputFile(companyManager.pathCompany);
                            check = false;
                            break;
                        case 3:
                            companyManager.displayAll(companyManager.getCompanyManager());
                            companyManager.delete(scanner);
                            companyManager.outputFile(companyManager.pathCompany);
                            check = false;
                            break;
                        case 4:
                            companyManager.displayAll(companyManager.getCompanyManager());
                            companyManager.searchName(scanner);
                            check = false;
                            break;
                        case 5:
                            companyManager.displayAll(companyManager.getCompanyManager());
                            companyManager.searchCode(scanner);
                            check =false;
                        case 6:
                            companyManager.displayAll(companyManager.getCompanyManager());
                            check = false;
                            break;
                    }
                } while (choice != 0);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter choice.");
            }
        }
    }
}
