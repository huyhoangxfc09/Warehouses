package MenuAdmin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdmin {
    static MenuProduct menuProduct = new MenuProduct();
    static MenuCompany menuCompany = new MenuCompany();
    static MenuInput menuInput = new MenuInput();
    static MenuOutput menuOutput = new MenuOutput();

   public void menu(Scanner scanner){
       int choice;
       boolean check = true;
       while (check) {
           try {
               do {
                   System.out.println("-----MENU ADMIN-----");
                   System.out.println("1. Menu product.");
                   System.out.println("2. Menu company provides. ");
                   System.out.println("3. Menu input warehouse. ");
                   System.out.println("4. Menu output warehouse. ");
                   System.out.println("0. Exit.");
                   System.out.println("Enter your choice: ");
                   choice = Integer.parseInt(scanner.nextLine());
                   switch (choice) {
                       case 1:
                           menuProduct.mainProduct(scanner);
                           check = false;
                           break;
                       case 2:
                           menuCompany.mainCompany(scanner);
                           check=false;
                           break;
                       case 3:
                           menuInput.mainInput(scanner);
                           check=false;
                           break;
                       case 4:
                           menuOutput.mainOutput(scanner);
                           check=false;
                           break;
                   }
               } while (choice != 0);
           } catch (InputMismatchException | NumberFormatException e) {
               System.out.println("Re-enter choice.");
           }
       }
   }
}
