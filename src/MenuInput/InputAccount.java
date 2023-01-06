package MenuInput;

import MenuAdmin.MenuCompany;
import MenuAdmin.MenuInput;
import MenuAdmin.MenuProduct;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputAccount {
    public  void menu(Scanner scanner){
        MenuInput menuInput = new MenuInput();
        MenuCompany menuCompany = new MenuCompany();
        MenuProduct menuProduct = new MenuProduct();
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU OUTPUT ACCOUNT-----");
                    System.out.println("1. Menu input warehouse. ");
                    System.out.println("2. Menu company provides. ");
                    System.out.println("3. Menu product. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuInput.mainInput(scanner);
                            check = false;
                            break;
                        case 2:
                            menuCompany.mainCompany(scanner);
                            check = false;
                            break;
                        case 3:
                            menuProduct.mainProduct(scanner);
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
