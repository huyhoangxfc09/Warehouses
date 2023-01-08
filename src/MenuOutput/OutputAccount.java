package MenuOutput;
import MenuAdmin.MenuOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OutputAccount {
    public void menu(Scanner scanner){
        MenuOutput menuOutput = new MenuOutput();
        MenuCustomer menuCustomer = new MenuCustomer();
        MenuBill menuBill = new MenuBill();
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("-----MENU OUTPUT ACCOUNT-----");
                    System.out.println("1. Menu output warehouse. ");
                    System.out.println("2. Menu customer. ");
                    System.out.println("3. Menu bill. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            menuOutput.mainOutput(scanner);
                            check = false;
                            break;
                        case 2:
                            menuCustomer.menu(scanner);
                            check = false;
                            break;
                        case 3:
                            menuBill.menu(scanner);
                            check = false;
                            break;
                        case 0:
                            check = false;

                    }
                } while (choice != 0);
            } catch (InputMismatchException | NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Re-enter choice.");
            }
        }
    }
}
