package Manager;

import OptionClass.Bill;
import OptionClass.Customer;
import OptionClass.OutputWarehouse;

import java.io.*;
import java.util.*;

public class BillManager {
    private ArrayList<Bill> listBill;
    private final OutputManager outputManager;
    private final CustomerManager customerManager;
    public BillManager(OutputManager outputManager, CustomerManager customerManager){
        listBill = new ArrayList<>();
        this.outputManager = outputManager;
        this.customerManager = customerManager;
    }
    public ArrayList<Bill> getListBill(){
        return listBill;
    }
    static  int index = 0;
    public  String pathBill = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Bill";
    public Bill create(Scanner scanner) {
        int number;
        if (listBill.isEmpty()) {
            number = index + 1;
        } else {
            number = listBill.get(listBill.size() - 1).getNumber() + 1;
        }
        Date date = new Date();
        outputManager.displayAll(outputManager.getListOutput());
        OutputWarehouse outputWarehouse = choiceOutput(scanner);
        customerManager.displayAll(customerManager.getListCustomer());
        Customer customer = choiceCustomer(scanner);
        return new Bill(number,date,customer,outputWarehouse);
    }


    public void add(Bill bill) {
        listBill.add(bill);
        System.out.println("Add bill successfully!");
        title();
        bill.displayBill();
    }


    public void delete(Scanner scanner) {
        System.out.println("Enter number delete: ");
        int number = Integer.parseInt(scanner.nextLine());
        Bill tempt = null;
        for (Bill e : listBill) {
            if (e.getNumber() == number) {
                tempt = e;
            }
        }
        if (tempt != null) {
            listBill.remove(tempt);
            title();
            tempt.displayBill();
            System.out.println("Delete successfully.");
        } else {
            System.out.println("Not in the list. Review.");
        }
    }

    public void displayAll(List<Bill> listBill) {
        if (!listBill.isEmpty()) {
            System.out.println("List bill:");
            title();
            for (Bill e : listBill) {
                e.displayBill();
            }
        } else {
            System.out.println("List empty.");
        }
    }


    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listBill);
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
                listBill = (ArrayList<Bill>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Customer choiceCustomer(Scanner scanner){
        Customer customer;
        boolean check = true;
        int id = 0;
        while (check) {
            try {
                System.out.println("Enter choice customer by number: ");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter.");
            }
        }
        customer = customerManager.getByNumber(id);
        if (customer!=null){
            return customer;
        }else {
            return choiceCustomer(scanner);
        }
    }

    public OutputWarehouse choiceOutput(Scanner scanner){
        OutputWarehouse output;
        boolean check = true;
        int id = 0;
        while (check) {
            try {
                System.out.println("Enter choice customer by number: ");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter.");
            }
        }
        output = outputManager.getByNumber(id);
        if (output!=null){
            return output;
        }else {
            return choiceOutput(scanner);
        }
    }
    public boolean checkCode(String code){
        for (Bill e : listBill){
            if (e.getOutputWarehouse().getCode().equals(code)){
                return true;
            }
        }return false;
    }

    public void searchByCode(Scanner scanner){
        System.out.println("Enter the code you want to search for: ");
        String code = scanner.nextLine();
        if (checkCode(code)){
            System.out.println("---Result---");
            for (Bill e : listBill)
                if (e.getOutputWarehouse().getCode().equals(code)){
                    title();
                    e.displayBill();
                }
        }else {
            System.out.println("Not found. Review!");
        }
    }

    public void title(){
        System.out.printf("%-10s%-30s%-20s%-25s%-20s%-20s%-30s%-10s%s",
                "NUMBER","DATE","CODE","NAME","CITIZEN ID","COMPANY","PRODUCT","QUANTITY","TOTAL\n");
    }
}
