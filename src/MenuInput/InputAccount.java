package MenuInput;

import MenuAdminManager.MenuInput;
import MenuAdminManager.MenuOutput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputAccount {
    public  void menu(Scanner scanner){
        MenuInput menuInput = new MenuInput();
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU OUTPUT ACCOUNT-----");
                    System.out.println("1. Menu output warehouse. ");
                    System.out.println("2. Out account management. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuInput.mainInput(scanner);
                            check = false;
                            break;
                        case 2:

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
