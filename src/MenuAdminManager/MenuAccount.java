package MenuAdminManager;

import Account.AccountManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAccount {
    static AccountManager accountManager = new AccountManager();
    public void menuAccountManage(Scanner scanner){
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU ACCOUNT-----");
                    System.out.println("1. Add new account. ");
                    System.out.println("2. Delete new account. ");
                    System.out.println("3. Display account. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            accountManager.creatAccount(scanner);
                            break;
                        case 2:
                            accountManager.deleteAccount(scanner);
                            break;
                        case 3:
                            accountManager.displayAccount();
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
