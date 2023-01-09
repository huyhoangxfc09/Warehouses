package Account;

import MenuInput.InputAccount;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputAccountManager {
    private List<Account> listAccountInput = new ArrayList<>();
    private final String REGEX = "^[A-Za-z0-9]{5,16}";
    public String pathInputAccount = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\InputAccount";
    public List<Account> getListAccountInput(){
        return listAccountInput;
    }

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
                if (!name.equalsIgnoreCase("admin")) {
                    if (checkAccount(name) != null) {
                        System.out.println("This account has already existed.");
                    } else {
                        check = false;
                    }
                }else {
                    System.out.println("Review!");
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
        listAccountInput.add(account);
    }
    public void update(Scanner scanner){
        System.out.println("Enter account name update: ");
        String name = scanner.nextLine();
        String password = null;
        String passwordRe;
        if (checkName(name)){
            boolean check = true;
            while (check) {
                System.out.println("Enter new password: ");
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
            while (check) {
                System.out.println("Confirm password: ");
                passwordRe = scanner.nextLine();
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(passwordRe);
                if ((matcher.matches())) {
                    if (passwordRe.equals(password)) {
                        for (Account e : listAccountInput){
                            if (e.getName().equals(name)){
                                e.setPassword(password);
                            }
                        }
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

    }
    public boolean checkName(String name) {
        for (Account e:listAccountInput) {
            if (e.getName().equals(name)){
                return true;
            }
        }return false;
    }


    public Account checkAccount(String name) {
        for (int i = 0; i < listAccountInput.size(); i++) {
            Account account = listAccountInput.get(i);
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
        for (Account e : listAccountInput) {
            if (e.getName().equals(name)){
                tempt =e;
            }
        }
        if (tempt!=null){
            listAccountInput.remove(tempt);
            System.out.println("Delete successfully!");
        }else {
            System.out.println("Failed to check again.");
        }
    }
    public void displayAccount(List<Account>listAccountInput){
        title();
        for (Account e: listAccountInput) {
            e.displayAccount();
        }
    }
    public void title(){
        System.out.printf("%-25s%s","ACCOUNT","PASSWORD\n");
    }

    public boolean checkAccountUser(String name, String password){
        for (Account e : listAccountInput) {
            if (name.equals(e.getName()) && password.equals(e.getPassword())){
               return true;
            }
        }return false;
    }
    public void loginUserInput(Scanner scanner){
        InputAccount inputAccount = new InputAccount();
        int count =0;
        while (count<=3){
            System.out.println("Enter account name: ");
            String name = scanner.nextLine();
            System.out.println("Enter account password: ");
            String password = scanner.nextLine();
            if (checkAccountUser(name,password)){
                System.out.println("Admin.Login successful.");
                inputAccount.menu(scanner);
                break;
            }else {
                System.out.println("Wrong account entered. Please re-enter.");
                count++;
            }
        }
    }
    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listAccountInput);
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
                listAccountInput = (List<Account>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
