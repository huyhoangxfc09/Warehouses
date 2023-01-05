package MenuAdminManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    public void admin(Scanner scanner){
        MenuAccount menuAccount = new MenuAccount();
        MenuAdmin menuAdmin = new MenuAdmin();
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU ACCOUNT-----");
                    System.out.println("1. Account Management. ");
                    System.out.println("2. Product Management. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuAccount.menuAccountManage(scanner);
                            check = false;
                            break;
                        case 2:
                            menuAdmin.menu(scanner);
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
