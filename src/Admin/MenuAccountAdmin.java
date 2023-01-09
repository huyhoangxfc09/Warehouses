package Admin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAccountAdmin {
    static LoginAdmin loginAdmin = new LoginAdmin();
    public void menuAccountManage(Scanner scanner) {
        loginAdmin.inputFile(loginAdmin.pathAdmin);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU ADMIN ACCOUNT-----");
                    System.out.println("1. Default account. ");
                    System.out.println("2. Update  admin account. ");
                    System.out.println("3. Display  admin account. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            loginAdmin.defaultAccount();
                            loginAdmin.outputFile(loginAdmin.pathAdmin);
                            check = false;
                            break;
                        case 2:
                            loginAdmin.displayAdmin(loginAdmin.getList());
                            loginAdmin.update(scanner);
                            loginAdmin.outputFile(loginAdmin.pathAdmin);
                            check = false;
                            break;
                        case 3:
                            loginAdmin.displayAdmin(loginAdmin.getList());
                            loginAdmin.outputFile(loginAdmin.pathAdmin);
                            check = false;
                            break;

                        case 0:
                            check = false;
                    }
                } while (choice != 0);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter choice.");
            }
        }
    }
}
