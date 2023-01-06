package Manager;

import OptionClass.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerManager {
    static int index = 0;
    private final String REGEX = "^[0-9]{12}";
    private ArrayList<Customer> listCustomer;

    public CustomerManager() {
        listCustomer = new ArrayList<>();
    }

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public String pathCustomer = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Customer";

    public Customer create(Scanner scanner) {
        int number;
        if (listCustomer.isEmpty()) {
            number = index + 1;
        } else {
            number = listCustomer.get(listCustomer.size() - 1).getNumber() + 1;
        }
        System.out.println("Enter the buyer's name: ");
        String name = scanner.nextLine();
        String citizen;
        int citizenID = 0;
        boolean check = true;
        while (check) {
            System.out.println("Enter citizen ID:");
            citizen = scanner.nextLine();
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(citizen);
            if (matcher.matches()) {
                citizenID = Integer.parseInt(citizen);
                check = false;
            }
        }
        System.out.println("Enter company name: ");
        String company = scanner.nextLine();
        System.out.println("Enter company address: ");
        String address = scanner.nextLine();
        System.out.println("Enter phone number: ");
        int phone = Integer.parseInt(scanner.nextLine());
        return new Customer(number, name, citizenID, company, address, phone);
    }

    public void add(Customer customer) {
        listCustomer.add(customer);
        System.out.println("Add customer successfully!");
        title();
        customer.displayCustomer();
    }

    public void update(Scanner scanner) {
        System.out.println("Enter number update: ");
        int number = Integer.parseInt(scanner.nextLine());
        boolean flag = false;
        for (Customer e : listCustomer) {
            if (e.getNumber() == number) {
                System.out.println("Enter the buyer's name: ");
                String name = scanner.nextLine();
                e.setName(name);
                String citizen;
                int citizenID = 0;
                boolean check = true;
                while (check) {
                    System.out.println("Enter citizen ID:");
                    citizen = scanner.nextLine();
                    Pattern pattern = Pattern.compile(REGEX);
                    Matcher matcher = pattern.matcher(citizen);
                    if (matcher.matches()) {
                        citizenID = Integer.parseInt(citizen);
                        check = false;
                    }
                }
                e.setCitizenID(citizenID);
                System.out.println("Enter company name: ");
                String company = scanner.nextLine();
                e.setCompany(company);
                System.out.println("Enter company address: ");
                String address = scanner.nextLine();
                e.setAddress(address);
                System.out.println("Enter phone number: ");
                int phone = Integer.parseInt(scanner.nextLine());
                e.setPhone(phone);
                System.out.println("Update successfully.");
                title();
                e.displayCustomer();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Not in the list. Review.");
        }
    }

    public void delete(Scanner scanner) {
        System.out.println("Enter number delete: ");
        int number = Integer.parseInt(scanner.nextLine());
        Customer tempt = null;
        for (Customer e : listCustomer) {
            if (e.getNumber() == number) {
                tempt = e;
            }
        }
        if (tempt != null) {
            listCustomer.remove(tempt);
            title();
            tempt.displayCustomer();
            System.out.println("Delete successfully.");
        } else {
            System.out.println("Not in the list. Review.");
        }
    }


    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listCustomer);
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
                listCustomer = (ArrayList<Customer>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void displayAll(List<Customer> listCustomer) {
        if (!listCustomer.isEmpty()) {
            System.out.println("List customer:");
            title();
            for (Customer e : listCustomer) {
                e.displayCustomer();
            }
        } else {
            System.out.println("List empty.");
        }
    }

    public void title() {
        System.out.printf("%-10s%-30s%-30s%-30s%-30s%s",
                "NUMBER", "NAME", "CITIZEN ID", "COMPANY", "ADDRESS", "PHONE NUMBER\n");
    }

    public void searchName(Scanner scanner){
        ArrayList<Customer> listByName = new ArrayList<>();
        System.out.println("Enter name search: ");
        String name = scanner.nextLine();
        for (Customer e : listCustomer){
            if (name.toLowerCase().contains(e.getName().toLowerCase())){
                listByName.add(e);
            }
        }
        System.out.println("--Result--");
        if (!listByName.isEmpty()){
            title();
            for (Customer e : listByName){
                e.displayCustomer();
            }
        }else {
            System.out.println("Not found.");
        }
    }
    public Customer getByNumber(int idProduct) {
        for (Customer e : listCustomer) {
            if (e.getNumber() == idProduct) {
                return e;
            }
        }
        return null;
    }

}
