package Admin;
import java.util.Scanner;

public class LoginAdmin {
    public void loginAdmin(Scanner scanner) {
        Admin admin = new Admin();
        int count = 0;
        while (count <= 3) {
            System.out.println("Enter account name: ");
            String name = scanner.nextLine();
            System.out.println("Enter account password: ");
            String password = scanner.nextLine();
            if (name.equalsIgnoreCase("admin")
                    && password.equalsIgnoreCase("admin123456")) {
                System.out.println("Admin login successful.");
                admin.admin(scanner);
                break;
            }else {
                System.err.println("Wrong account entered. Please re-enter.");
                count++;
            }
        }
    }
}
