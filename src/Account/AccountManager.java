package Account;

import MenuAdminManager.MenuAdmin;
import MenuUserManager.MenuUser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountManager {
    private Map<String, String> mapAccount = new HashMap<String, String>();
    private List<Account> listAccount = new ArrayList<>();
    private final String REGEX = "^[A-Za-z1-9]{5,16}";

    public void creatAccount(Scanner scanner) {
        boolean check = true;
        String name = null;
        String password = null;
        String passwordRe;
        while (check) {
            System.out.println("Enter account name: ");
            name = scanner.nextLine();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                if (name.equalsIgnoreCase("admin")) {
                    System.out.println("Can't create with this name");
                } else {
                    check = false;
                }
                if (checkAccount(name) != null) {
                    System.out.println("This account has already existed.");
                } else {
                    check = false;
                }
            } else {
                System.out.println("Account must be at least 5 characters and no special characters");
            }
        }
        check = true;
        while (check) {
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(password);
            if ((matcher.matches())) {
                check = false;
            } else {
                System.out.println("Password must be at least 5 characters and no special characters");
            }
        }
        check = true;
        System.out.println("Confirm password: ");
        while (check) {
            System.out.println("Enter password: ");
            passwordRe = scanner.nextLine();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(passwordRe);
            if ((matcher.matches())) {
                if (passwordRe.equals(password)) {
                    check = false;
                } else {
                    System.out.println("Password incorrect. Please re-enter.");
                }
            } else {
                System.out.println("Password must be at least 5 characters and no special characters");
            }
        }
        System.out.println("Successfully added account.");
        Account account = new Account(name, password);
        listAccount.add(account);
    }

    public Account checkAccount(String name) {
        for (int i = 0; i < listAccount.size(); i++) {
            Account account = listAccount.get(i);
            if (name.equalsIgnoreCase(account.getName())) {
                return account;
            }
        }
        return null;
    }
    public void deleteAccount(Scanner scanner){
        System.out.println("Enter the account name to delete: ");
        String name = scanner.nextLine();
        Account tempt = null;
        for (Account e : listAccount) {
            if (e.getName().equals(name)){
                tempt =e;
            }
        }
        if (tempt!=null){
            listAccount.remove(tempt);
        }else {
            System.out.println("Failed to check again.");
        }
    }
    public void displayAccount(){
        title();
        for (Account e:listAccount) {
            e.displayAccount();
        }
    }
    public void title(){
        System.out.printf("%-25s%s","ACCOUNT","PASSWORD\n");
    }
    public void loginAdmin(Scanner scanner) {
        MenuAdmin menuAdmin = new MenuAdmin();
        int count = 0;
        while (count <= 3) {
            System.out.println("Enter account name: ");
            String name = scanner.nextLine();
            System.out.println("Enter account password: ");
            String password = scanner.nextLine();
            if (name.equalsIgnoreCase("admin")
                    & password.equalsIgnoreCase("admin123456")) {
                System.out.println("Admin login successful.");
                menuAdmin.menu(scanner);
                break;
            }else {
                System.err.println("Wrong account entered. Please re-enter.");
                count++;
            }
        }
    }
    public boolean checkAccountUser(String name, String password){
        for (Account e : listAccount) {
            if (name.equals(e.getName())& password.equals(e.getPassword())){
               return true;
            }
        }return false;
    }
    public void loginUser(Scanner scanner){
        MenuUser menuUser = new MenuUser();
        int count =0;
        while (count<=3){
            System.out.println("Enter account name: ");
            String name = scanner.nextLine();
            System.out.println("Enter account password: ");
            String password = scanner.nextLine();
            if (checkAccountUser(name,password)){
                System.out.println("Login successful.");
                menuUser.menu(scanner);
            }else {
                System.out.println("Wrong account entered. Please re-enter.");
                count++;
            }
        }


    }

}
