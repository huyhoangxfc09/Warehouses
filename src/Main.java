import Account.AccountManager;
import MenuAdminManager.Admin;
import MenuAdminManager.MenuAdmin;

import java.util.Scanner;

public class Main {
   static Admin admin = new Admin();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        admin.admin(scanner);
    }
}