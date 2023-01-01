package Manager;

import OptionClass.Company;
import OptionClass.Product;
import OptionClass.RegisteredVehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CompanyManager implements CRUD<Company> {
    private ArrayList<Company> listCompany;
    private final ProductManager productManager;
    private final RegisteredVehicleManager registeredVehicleManager;
    static int index = 0;

    public CompanyManager(ProductManager productManager, RegisteredVehicleManager registeredVehicleManager) {
        listCompany = new ArrayList<>();
        this.productManager = productManager;
        this.registeredVehicleManager = registeredVehicleManager;
    }

    public ArrayList<Company> getCompanyManager() {
        return listCompany;
    }

    @Override
    public Company create(Scanner scanner) {
        int number;
        if (listCompany.isEmpty()) {
            number = index + 1;
        } else {
            number = listCompany.get(listCompany.size() - 1).getNumber() + 1;
        }
        System.out.println("Enter company name: ");
        String name = scanner.nextLine();
        productManager.displayAll(productManager.getListProduct());
        Product product = choiceProduct(scanner);
        registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
        RegisteredVehicle registeredVehicle = choiceCar(scanner);
        return new Company(number,name,product,registeredVehicle);
    }

    @Override
    public void add(Company company) {
        listCompany.add(company);
        System.out.println("Add company successfully!");
        titleCompany();
        company.displayCompany();
    }

    @Override
    public void update(Scanner scanner) {
        int number = 0;
        boolean check = true;
        while (check) {
            try {
                System.out.println("Enter the delete order number: ");
                number = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter number.");
            }
        }
        if (checkNumber(number)){
            for (Company e : listCompany){
                if (e.getNumber()==number){
                    System.out.println("Enter company name: ");
                    String name = scanner.nextLine();
                    if (!name.equals("")){
                        e.setName(name);
                    }
                    System.out.println("Do you want to change the product?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceP = scanner.nextLine();
                    if (choiceP.equalsIgnoreCase("Y")){
                        productManager.displayAll(productManager.getListProduct());
                        Product product = choiceProduct(scanner);
                        e.setProduct(product);
                    }
                    System.out.println("Do you want to change the car?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceC = scanner.nextLine();
                    if (choiceC.equalsIgnoreCase("Y")){
                        registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
                        RegisteredVehicle car = choiceCar(scanner);
                        e.setLicensePlates(car);
                    }
                    System.out.println("Update company successfully!");
                    titleCompany();
                    e.displayCompany();

                }
            }
        }else {
            System.out.println("There is no sequence number in the list.");
        }
    }

    @Override
    public void delete(Scanner scanner) {
        int number = 0;
        boolean check = true;
        while (check) {
            try {
                System.out.println("Enter the delete order number: ");
                number = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter number.");
            }
        }
        int index = 0;
        if (checkNumber(number)) {
            Company tempt = null;
            for (Company e : listCompany) {
                if (e.getNumber()==number) {
                    tempt = e;
                    index = e.getNumber();
                    titleCompany();
                    e.displayCompany();
                }
            }
            for (Company e : listCompany) {
                if (e.getNumber() > index) {
                    e.setNumber(e.getNumber() - 1);
                }
            }
            listCompany.remove(tempt);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("There is no code to delete in the list.");
        }
    }

    @Override
    public void displayAll(List<Company> element) {
        if (listCompany != null) {
            System.out.println("List product: ");
            titleCompany();
            for (Company e : listCompany) {
                e.displayCompany();
            }
        } else {
            System.out.println("There are no products in the list.");
        }
    }

    @Override
    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listCompany);
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
                listCompany = (ArrayList<Company>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void titleCompany() {
        System.out.printf("%-10s%-25s%-15s%-30s%-20s%s",
                "NUMBER", "NAME", "CODE", "PRODUCT", "TYPE", "CAR\n");
    }

    public Product choiceProduct(Scanner scanner) {
        Product product;
        boolean check = true;
        int idProduct = 0;
        while (check) {
            try {
                System.out.println("Enter choice product by number: (Enter 0 for create new)");
                idProduct = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter number product.");
            }
        }
        if (idProduct == 0) {
            product = productManager.create(scanner);
            productManager.add(product);
            productManager.outputFile(productManager.pathProduct);
            productManager.inputFile(productManager.pathProduct);
        } else {
            product = productManager.getByNumber(idProduct);
        }
        if (product!=null){
            return product;
        }else {
            return choiceProduct(scanner);
        }
    }
    public RegisteredVehicle choiceCar(Scanner scanner) {
        RegisteredVehicle car;
        boolean check = true;
        int idCar = 0;
        while (check) {
            try {
                System.out.println("Enter choice car by number: (Enter 0 for create new)");
                idCar = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter number product.");
            }
        }
        if (idCar == 0) {
            car = registeredVehicleManager.create(scanner);
            registeredVehicleManager.add(car);
            registeredVehicleManager.outputFile(registeredVehicleManager.pathVehicle);
            registeredVehicleManager.inputFile(registeredVehicleManager.pathVehicle);
        } else {
            car = registeredVehicleManager.getByNumber(idCar);
        }
        if (car!=null){
            return car;
        }else {
            return choiceCar(scanner);
        }
    }
    public boolean checkNumber(int number){
        for (Company e: listCompany){
            if (e.getNumber()==number){
                return true;
            }
        }
        return false;
    }
    public void searchName(Scanner scanner){
        ArrayList<Company> listName = new ArrayList<>();
        System.out.println("Enter the name of the company you are looking for: ");
        String nameSearch = scanner.nextLine();
        for (Company e : listCompany){
            if (e.getName().toLowerCase().contains(nameSearch.toLowerCase())){
                listName.add(e);
            }
        }
        if (!listName.isEmpty()){
            titleCompany();
            for (Company e : listCompany){
                e.displayCompany();
            }
        }else {
            System.out.println("Name not found in the list.");
        }
    }

    public void  searchCode(Scanner scanner){
        ArrayList<Company> listCode= new ArrayList<>();
        System.out.println("Enter the code to search: ");
        String codeSearch = scanner.nextLine();
        registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
        for (Company e : listCompany){
            if (e.getProduct().getCode().equals(codeSearch)){
               listCode.add(e);
            }
        }
        if (!listCode.isEmpty()){
            titleCompany();
            for (Company e : listCode){
                e.displayCompany();
            }
        }else {
            System.out.println("Code not found in the list.");
        }
    }

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ProductManager productManager = new ProductManager();
            RegisteredVehicleManager registeredVehicleManager = new RegisteredVehicleManager();
            CompanyManager companyManager = new CompanyManager(productManager,registeredVehicleManager);
            String path = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Company";
            productManager.inputFile(productManager.pathProduct);
            registeredVehicleManager.inputFile(registeredVehicleManager.pathVehicle);
            companyManager.inputFile(path);
            int choice;
            boolean check = true;
            while (check) {
                try {
                    do {
                        System.out.println("MENU VEHICLE:");
                        System.out.println("1. Add new company. ");
                        System.out.println("2. Update company by number. ");
                        System.out.println("3. Delete company by number. ");
                        System.out.println("4. Show companies by name. ");
                        System.out.println("5. Show companies by code. ");
                        System.out.println("6. Display all company. ");
                        System.out.println("0. Exit.");
                        System.out.println("Enter your choice: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1:
                                Company company = companyManager.create(scanner);
                                companyManager.add(company);
                                companyManager.outputFile(path);
                                check = false;
                                break;
                            case 2:
                                companyManager.displayAll(companyManager.getCompanyManager());
                                companyManager.update(scanner);
                                companyManager.outputFile(path);
                                check = false;
                                break;
                            case 3:
                                companyManager.displayAll(companyManager.getCompanyManager());
                                companyManager.delete(scanner);
                                companyManager.outputFile(path);
                                check = false;
                                break;
                            case 4:
                                companyManager.displayAll(companyManager.getCompanyManager());
                                companyManager.searchName(scanner);
                                check = false;
                                break;
                            case 5:
                                companyManager.displayAll(companyManager.getCompanyManager());
                                companyManager.searchCode(scanner);
                                check = false;
                                break;
                            case 6:
                                companyManager.displayAll(companyManager.getCompanyManager());
                                check = false;
                                break;
                        }
                    } while (choice != 0);
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Re-enter choice.");
                }
            }
        }


}
