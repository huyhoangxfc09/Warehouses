package Admin;
import Account.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAdmin {
    public final String REGEX = "^[A-Za-z0-9]{5,16}";
    public String pathAdmin = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\AdminAccount";
    List<Account> list = new ArrayList<>();
    public List<Account> getList(){
        return list;
    }
    public void defaultAccount(){
        String password = "admin123456";
        list.get(0).setPassword(password);
    }
    public void loginAdmin(Scanner scanner) {
        Admin admin = new Admin();
        int count = 0;
        while (count <= 3) {
            System.out.println("Enter account name: ");
            String name = scanner.nextLine();
            System.out.println("Enter account password: ");
            String password = scanner.nextLine();
            if (checkAccountAdmin(name,password)){
                System.out.println("Admin login successful.");
                admin.admin(scanner);
                break;
            }else {
                System.err.println("Wrong account entered. Please re-enter.");
                count++;
            }
        }
    }
    public boolean checkAccountAdmin(String name, String password){
        for (Account e : list) {
            if (name.equals(e.getName()) && password.equals(e.getPassword())){
                return true;
            }
        }return false;
    }
    public void update(Scanner scanner) {
        String password = null;
        String passwordRe;
        boolean check = true;
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
                    list.get(0).setPassword(password);
                    System.out.println();
                    System.out.println("Change password successfully!");
                    check = false;
                } else {
                    System.out.println("Password incorrect. Please re-enter.");
                }
            } else {
                System.out.println("Password must be at least 5 characters and no special characters");
            }
        }
    }
    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void inputFile(String path) {
        File file = new File(path);
        try {
            InputStream is = new FileInputStream(file);
            if (is.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(is);
                list = (List<Account>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void displayAdmin(List<Account>list){
        title();
        list.get(0).displayAccount();
    }
    public void title(){
        System.out.printf("%-25s%s","ACCOUNT","PASSWORD\n");
    }
}
