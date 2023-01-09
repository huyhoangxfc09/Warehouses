package Manager;

import OptionClass.Company;
import OptionClass.InputWarehouse;

import java.io.*;
import java.util.*;

public class InputManager implements CRUD<InputWarehouse> {
    private ArrayList<InputWarehouse> listInput;
    private   final CompanyManager companyManager;
    static int index = 0;

    public InputManager(CompanyManager companyManager) {
        listInput = new ArrayList<>();
        this.companyManager = companyManager;
    }
    public ArrayList<InputWarehouse> getListInput(){
        return listInput;
    }
    public String pathInput = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Input";
    @Override
    public InputWarehouse create(Scanner scanner) {
        int number;
        int quantity = 0;
        if (listInput.isEmpty()) {
            number = index + 1;
        } else {
            number = listInput.get(listInput.size() - 1).getNumber() + 1;
        }
        Date date = new Date();
        System.out.println("Enter warehouse code: ");
        String code = scanner.nextLine();
        if (!checkCode(code)) {
            companyManager.displayAll(companyManager.getCompanyManager());
            Company company = choiceCompany(scanner);
            boolean check = true;
            while (check){
                try {
                    System.out.println("Enter quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    check = false;
                }catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Re-enter quantity.");
                }
            }
            return new InputWarehouse(number, date, code, company,quantity);
        }
        return null;
    }

    @Override
    public void add(InputWarehouse inputWareHouse) {
        if (inputWareHouse != null) {
            listInput.add(inputWareHouse);
            System.out.println("Add input warehouse successfully!");
            titleInput();
            inputWareHouse.displayInput();
        } else {
            System.out.println("Input warehouse code already exists.");
        }
    }

    @Override
    public void update(Scanner scanner) {
        System.out.println("Enter code to update: ");
        String code = scanner.nextLine();
        if (checkCode(code)) {
            for (InputWarehouse e : listInput){
                if (e.getCode().equals(code)){
                    System.out.println("Do you want to change the date?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceD = scanner.nextLine();
                    if (choiceD.equalsIgnoreCase("Y")){
                        e.setDate(new Date());
                    }
                    System.out.println("Do you want to change the code?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceC = scanner.nextLine();
                    if (choiceC.equalsIgnoreCase("Y")){
                        System.out.println("Enter warehouse code: ");
                        String codeUpdate = scanner.nextLine();
                        e.setCode(codeUpdate);
                    }
                    System.out.println("Do you want to change the company?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceP = scanner.nextLine();
                    if (choiceP.equalsIgnoreCase("Y")){
                        companyManager.displayAll(companyManager.getCompanyManager());
                        Company company = choiceCompany(scanner);
                        e.setCompany(company);
                    }
                    int quantity = 0;
                    boolean check = true;
                    while (check){
                        try {
                            System.out.println("Enter quantity: ");
                            quantity = Integer.parseInt(scanner.nextLine());
                            check = false;
                        }catch (InputMismatchException | NumberFormatException exception) {
                            System.out.println("Re-enter quantity.");
                        }
                    }
                    e.setQuantity(quantity);
                    System.out.println("Update input warehouse successfully!");
                    titleInput();
                    e.displayInput();
                }
            }
        } else {
            System.out.println("There is no code in the list.");
        }
    }

    @Override
    public void delete(Scanner scanner) {
        System.out.println("Enter code to delete. ");
        String code = scanner.nextLine();
        int index = 0;
        if (checkCode(code)) {
            InputWarehouse tempt = null;
            for (InputWarehouse e : listInput) {
                if (e.getCode().equals(code)) {
                    tempt = e;
                    index = e.getNumber();
                    titleInput();
                    e.displayInput();
                }
            }
            for (InputWarehouse e : listInput) {
                if (e.getNumber() > index) {
                    e.setNumber(e.getNumber() - 1);
                }
            }
            listInput.remove(tempt);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("There is no code to delete in the list.");
        }
    }

    @Override
    public void displayAll(List<InputWarehouse> element) {
        if (listInput != null) {
            System.out.println("List input warehouse: ");
            titleInput();
            for (InputWarehouse e : listInput) {
                e.displayInput();
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
            oos.writeObject(listInput);
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
                listInput = (ArrayList<InputWarehouse>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkCode(String code) {
        for (InputWarehouse e : listInput) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public Company choiceCompany(Scanner scanner) {
        Company company;
        boolean check = true;
        int idCompany = 0;
        while (check) {
            try {
                System.out.println("Enter choice product by number: (Enter 0 for create new)");
                idCompany = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter.");
            }
        }
        if (idCompany == 0) {
            company = companyManager.create(scanner);
            companyManager.add(company);
            companyManager.outputFile(companyManager.pathCompany);
            companyManager.inputFile(companyManager.pathCompany);
        } else {
            company = companyManager.getByNumber(idCompany);
        }
        if (company != null) {
            return company;
        } else {
            return choiceCompany(scanner);
        }
    }
    
    public void titleInput() {
        System.out.printf("%-10s%-30s%-20s%-20s%-20s%-30s%-20s%s",
                "NUMBER", "DATE", "CODE", "COMPANY", "CODE PRODUCT", "PRODUCT", "TYPE", "QUANTITY\n");
    }
    public InputWarehouse getByNumber(int id){
        for (InputWarehouse e: listInput){
            if (e.getNumber()==id){
                return e;
            }
        }
        return null;
    }

    public void searchByCode(Scanner scanner){
        System.out.println("Enter the code to enter the search store: ");
        String code = scanner.nextLine();
        InputWarehouse saveCode = null;
        if (!listInput.isEmpty()){
            for (InputWarehouse e : listInput){
                if (e.getCode().equals(code)){
                    saveCode = e;
                }
            }
            System.out.println("---RESULT---");
            if (saveCode!=null){
                titleInput();
                saveCode.displayInput();
            }else {
                System.out.println("Not found.");
            }
        }else {
            System.out.println("The list is empty.");
        }
    }
    public String choiceType(Scanner scanner) {
        int choice;
        String type = "";
        do {
            System.out.println("1. Laptop.");
            System.out.println("2. Mobile phone");
            System.out.println("3. Other");
            System.out.println("Enter choice: (1 -> 3) ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    type = "Laptop";
                    break;
                case 2:
                    type = "Mobile phone";
                    break;
                case 3:
                    type = "Other";
                    break;
            }
        } while (choice < 0 || choice > 3);
        return type;
    }
    public void searchType(Scanner scanner){
        ArrayList<InputWarehouse> listType = new ArrayList<>();
        System.out.println("Enter the type of the product you want to search for: ");
        String typeSearch = choiceType(scanner);
        for (InputWarehouse e : listInput){
            if (e.getCompany().getProduct().getType().equals(typeSearch)){
                listType.add(e);
            }
        }
        System.out.println("---RESULT---");
        if (!listType.isEmpty()){
            titleInput();
            for (InputWarehouse e : listType){
                e.displayInput();
            }
        }else {
            System.out.println("Not found.");
        }
    }
}
