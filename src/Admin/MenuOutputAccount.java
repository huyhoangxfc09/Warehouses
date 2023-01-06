package Admin;
import Account.OutputAccountManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOutputAccount {
    static OutputAccountManager outputAccountManager = new OutputAccountManager();
    public void menuAccountManage(Scanner scanner){
        outputAccountManager.inputFile(outputAccountManager.pathOutputAccount);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU OUTPUT ACCOUNT-----");
                    System.out.println("1. Add new account. ");
                    System.out.println("2. Delete new account. ");
                    System.out.println("3. Update account. ");
                    System.out.println("4. Display account. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            outputAccountManager.creatAccount(scanner);
                            outputAccountManager.outputFile(outputAccountManager.pathOutputAccount);
                            check =false;
                            break;
                        case 2:
                            outputAccountManager.deleteAccount(scanner);
                            outputAccountManager.outputFile(outputAccountManager.pathOutputAccount);
                            check =false;
                            break;
                        case 3:
                            outputAccountManager.update(scanner);
                            outputAccountManager.outputFile(outputAccountManager.pathOutputAccount);
                            check =false;
                            break;
                        case 4:
                            outputAccountManager.displayAccount();
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
