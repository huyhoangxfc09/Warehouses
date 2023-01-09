package Admin;

import MenuAdmin.MenuAdmin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    public void admin(Scanner scanner){
        MenuAccountAdmin menuAccountAdmin =new MenuAccountAdmin();
        MenuInputAccount menuInputAccount = new MenuInputAccount();
        MenuOutputAccount menuOutputAccount = new MenuOutputAccount();
        MenuAdmin menuAdmin = new MenuAdmin();
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU ADMIN-----");
                    System.out.println("1. Admin account management. ");
                    System.out.println("2. Input account management. ");
                    System.out.println("3. Out account management. ");
                    System.out.println("4. Product Management. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuAccountAdmin.menuAccountManage(scanner);
                            check = false;
                        case 2:
                            menuInputAccount.menuAccountManage(scanner);
                            check = false;
                            break;
                        case 3:
                            menuOutputAccount.menuAccountManage(scanner);
                            check = false;
                            break;
                        case 4:
                            menuAdmin.menu(scanner);
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
