package Admin;

import Account.InputAccountManager;
import Account.OutputAccountManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    static LoginAdmin loginAdmin = new LoginAdmin();
    static InputAccountManager inputAccountManager = new InputAccountManager();
    static OutputAccountManager outputAccountManager = new OutputAccountManager();
    public void login(Scanner scanner){
        outputAccountManager.inputFile(outputAccountManager.pathOutputAccount);
        inputAccountManager.inputFile(inputAccountManager.pathInputAccount);
        loginAdmin.inputFile(loginAdmin.pathAdmin);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU ACCOUNT-----");
                    System.out.println("1. Login admin. ");
                    System.out.println("2. Login Staff Output. ");
                    System.out.println("3. Login Staff Input. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            loginAdmin.loginAdmin(scanner);
                            check = false;
                            break;
                        case 2:
                            outputAccountManager.loginUserOut(scanner);
                            check = false;
                            break;
                        case 3:
                            inputAccountManager.loginUserInput(scanner);
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
