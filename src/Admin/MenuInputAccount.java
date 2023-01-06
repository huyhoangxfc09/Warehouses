package Admin;

import Account.InputAccountManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInputAccount {
    static InputAccountManager inputAccountManager = new InputAccountManager();
    public void menuAccountManage(Scanner scanner){
        inputAccountManager.inputFile(inputAccountManager.pathInputAccount);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU INPUT ACCOUNT-----");
                    System.out.println("1. Add new account. ");
                    System.out.println("2. Delete new account. ");
                    System.out.println("3. Display account. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            inputAccountManager.creatAccount(scanner);
                            inputAccountManager.outputFile(inputAccountManager.pathInputAccount);
                            check =false;
                            break;
                        case 2:
                            inputAccountManager.deleteAccount(scanner);
                            inputAccountManager.outputFile(inputAccountManager.pathInputAccount);
                            check =false;
                            break;
                        case 3:
                            inputAccountManager.displayAccount();
                            check =false;
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
