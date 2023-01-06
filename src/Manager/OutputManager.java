package Manager;

import OptionClass.Customer;
import OptionClass.InputWarehouse;
import OptionClass.OutputWarehouse;

import java.io.*;
import java.util.*;

public class OutputManager implements CRUD<OutputWarehouse> {
    private ArrayList<OutputWarehouse> listOutput;
    private  final InputManager inputManager;
    public final CustomerManager customerManager;
    static int index = 0;

    public OutputManager(InputManager inputManager, CustomerManager customerManager) {
        listOutput = new ArrayList<>();
        this.inputManager = inputManager;
        this.customerManager = customerManager;
    }
    public  String pathOutput = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Output";

    public ArrayList<OutputWarehouse> getListOutput() {
        return listOutput;
    }

    @Override
    public OutputWarehouse create(Scanner scanner) {
        int number;
        int quantity = 0;
        if (listOutput.isEmpty()) {
            number = index + 1;
        } else {
            number = listOutput.get(listOutput.size() - 1).getNumber() + 1;
        }
        Date date = new Date();
        System.out.println("Enter warehouse code: ");
        String code = scanner.nextLine();
        if (!checkCode(code)) {
            customerManager.displayAll(customerManager.getListCustomer());
            Customer customer = choiceCompany(scanner);
            inputManager.displayAll(inputManager.getListInput());
            InputWarehouse inputWarehouse = choiceInput(scanner);
            double tempt = inputWarehouse.getCompany().getProduct().getPrice();
            boolean check = true;
            while (check){
                try {
                    System.out.println("Enter quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (0<quantity){
                        assert inputWarehouse != null;
                        if (quantity<=inputWarehouse.getQuantity()){
                            inputWarehouse.setQuantity(inputWarehouse.getQuantity()-quantity);
                            inputManager.outputFile(inputManager.pathInput);
                            inputManager.inputFile(inputManager.pathInput);
                            check = false;
                        }else {
                            System.out.println("Quantity is not enough, please check again.");
                        }
                    }

                }catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Re-enter quantity.");
                }
            }
            double total = quantity*tempt;
            return new OutputWarehouse(number,date,code,customer,inputWarehouse,quantity,total);
        }
        return null;
    }

    @Override
    public void add(OutputWarehouse outputWarehouse) {
        if (outputWarehouse != null) {
            listOutput.add(outputWarehouse);
            System.out.println("Add input warehouse successfully!");
            titleOutput();
            outputWarehouse.displayOutput();
        } else {
            System.out.println("Input warehouse code already exists.");
        }
    }

    @Override
    public void update(Scanner scanner) {
        System.out.println("Enter the delivery code: ");
        String code =scanner.nextLine();
        if (checkCode(code)){
            for (OutputWarehouse e : listOutput){
                if (e.getCode().equals(code)){
                    e.setDate(new Date());
                    customerManager.displayAll(customerManager.getListCustomer());
                    Customer customer = choiceCompany(scanner);
                    e.setCustomer(customer);
                    System.out.println("Do you want to change the product?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceP = scanner.nextLine();
                    InputWarehouse inputWarehouse = null;
                    double tempt = 0;
                    if (choiceP.equalsIgnoreCase("Y")){
                        InputWarehouse inputWarehouse1 = checkInputCode(e.getInputWarehouse().getCode());
                        tempt = inputWarehouse1.getCompany().getProduct().getPrice();
                        inputWarehouse1.setQuantity(e.getQuantity()+inputWarehouse1.getQuantity());
                        inputManager.outputFile(inputManager.pathInput);
                        inputManager.inputFile(inputManager.pathInput);
                        inputManager.displayAll(inputManager.getListInput());
                        inputWarehouse = choiceInput(scanner);
                        e.setInputWarehouse(inputWarehouse);
                    }
                    int quantity = 0;
                    boolean check = true;
                    while (check){
                        try {
                            System.out.println("Enter quantity: ");
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (0<quantity){
                                assert inputWarehouse != null;
                                if (quantity<=inputWarehouse.getQuantity()){
                                    inputWarehouse.setQuantity(inputWarehouse.getQuantity()-quantity);
                                    inputManager.outputFile(inputManager.pathInput);
                                    inputManager.inputFile(inputManager.pathInput);
                                    check = false;
                                }else {
                                    System.out.println("Quantity is not enough, please check again.");
                                }
                            }
                        }catch (InputMismatchException | NumberFormatException exception) {
                            System.out.println("Re-enter quantity.");
                        }
                    }
                    e.setQuantity(quantity);
                    double total = quantity*tempt;
                    e.setTotal(total);
                    System.out.println("Update successful.");
                }
            }
        }
    }

    @Override
    public void delete(Scanner scanner) {
        System.out.println("Enter code to delete. ");
        String code = scanner.nextLine();
        int index = 0;
        if (checkCode(code)) {
            OutputWarehouse tempt = null;
            for (OutputWarehouse e : listOutput) {
                if (e.getCode().equals(code)) {
                    InputWarehouse inputWarehouse = checkInputCode(e.getInputWarehouse().getCode());
                    inputWarehouse.setQuantity(e.getQuantity()+inputWarehouse.getQuantity());
                    inputManager.outputFile(inputManager.pathInput);
                    inputManager.inputFile(inputManager.pathInput);
                    tempt = e;
                    index = e.getNumber();
                    titleOutput();
                    e.displayOutput();
                }
            }
            for (OutputWarehouse e : listOutput) {
                if (e.getNumber() > index) {
                    e.setNumber(e.getNumber() - 1);
                }
            }
            listOutput.remove(tempt);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("There is no code to delete in the list.");
        }
    }

    @Override
    public void displayAll(List<OutputWarehouse> element) {
        if (listOutput != null) {
            System.out.println("List output warehouse: ");
            titleOutput();
            for (OutputWarehouse e : listOutput) {
                e.displayOutput();
            }
        } else {
            System.out.println("There are no input warehouse in the list.");
        }
    }

    @Override
    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listOutput);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inputFile(String path) {
        File file = new File(path);
        try {
            InputStream is = new FileInputStream(file);
            if (is.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(is);
                listOutput = (ArrayList<OutputWarehouse>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkCode(String code) {
        for (OutputWarehouse e : listOutput) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public InputWarehouse choiceInput(Scanner scanner) {
        InputWarehouse inputWarehouse;
        boolean check = true;
        int id = 0;
        while (check) {
            try {
                System.out.println("Enter choice product by number: ");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter.");
            }
        }
        inputWarehouse = inputManager.getByNumber(id);
        if (inputWarehouse!=null){
            return inputWarehouse;
        }else {
            return choiceInput(scanner);
        }
    }
    public Customer choiceCompany(Scanner scanner) {
        Customer customer;
        boolean check = true;
        int id = 0;
        while (check) {
            try {
                System.out.println("Enter choice company by number: (Enter 0 for creat new.)");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter.");
            }
        }
        if (id==0){
            customer = customerManager.create(scanner);
            customerManager.add(customer);
            customerManager.outputFile(customerManager.pathCustomer);
            customerManager.inputFile(customerManager.pathCustomer);
        }else {
            customer = customerManager.getByNumber(id);
        }
        if (customer!=null){
            return customer;
        }else {
            return choiceCompany(scanner);
        }
    }

    public void titleOutput() {
        System.out.printf("%-10s%-30s%-20s%-25s%-25s%-25s%-10s%s",
                "NUMBER", "DATE", "CODE", "NAME", "PRODUCT","INPUT CODE", "QUANTITY", "TOTAL\n");
    }
    public InputWarehouse checkInputCode(String inputCode){
        InputWarehouse inputWarehouse = null;
        for (InputWarehouse e : inputManager.getListInput()){
            if (e.getCode().equals(inputCode)){
                inputWarehouse = e;
            }
        }return inputWarehouse;
    }
    public void searchByCode(Scanner scanner){
        System.out.println("Enter the code to enter the search store: ");
        String code = scanner.nextLine();
        OutputWarehouse saveCode = null;
        if (!listOutput.isEmpty()){
            for (OutputWarehouse e : listOutput){
                if (e.getCode().equals(code)){
                    saveCode = e;
                }
            }
            System.out.println("---RESULT---");
            if (saveCode!=null){
                titleOutput();
                saveCode.displayOutput();
            }else {
                System.out.println("Not found.");
            }
        }else {
            System.out.println("The list is empty.");
        }
    }
}
