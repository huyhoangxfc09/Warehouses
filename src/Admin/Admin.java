package Admin;

import MenuAdmin.MenuAdmin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    public void admin(Scanner scanner){
        MenuInputAccount menuInputAccount = new MenuInputAccount();
        MenuOutputAccount menuOutputAccount = new MenuOutputAccount();
        MenuAdmin menuAdmin = new MenuAdmin();
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU ADMIN-----");
                    System.out.println("1. Input account management. ");
                    System.out.println("2. Out account management. ");
                    System.out.println("3. Product Management. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuInputAccount.menuAccountManage(scanner);
                            check = false;
                            break;
                        case 2:
                            menuOutputAccount.menuAccountManage(scanner);
                            check = false;
                            break;
                        case 3:
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
