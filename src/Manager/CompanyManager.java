package Manager;

import OptionClass.Company;
import OptionClass.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CompanyManager implements CRUD<Company> {
    private ArrayList<Company> listCompany;
    private final ProductManager productManager;
    static int index = 0;

    public CompanyManager(ProductManager productManager) {
        listCompany = new ArrayList<>();
        this.productManager = productManager;
    }

    public String pathCompany = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Company";

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
        return new Company(number, name, product);
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
                System.out.println("Enter the update order number: ");
                number = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter number.");
            }
        }
        if (checkNumber(number)) {
            for (Company e : listCompany) {
                if (e.getNumber() == number) {
                    System.out.println("Enter company name: ");
                    String name = scanner.nextLine();
                    if (!name.equals("")) {
                        e.setName(name);
                    }
                    System.out.println("Do you want to change the product?");
                    System.out.println("Enter Y to update and any keyword to skip: ");
                    String choiceP = scanner.nextLine();
                    if (choiceP.equalsIgnoreCase("Y")) {
                        productManager.displayAll(productManager.getListProduct());
                        Product product = choiceProduct(scanner);
                        e.setProduct(product);
                    }
                    System.out.println("Update company successfully!");
                    titleCompany();
                    e.displayCompany();

                }
            }
        } else {
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
                if (e.getNumber() == number) {
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
                "NUMBER", "NAME", "CODE", "PRODUCT", "TYPE","PRICE\n");
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
        if (product != null) {
            return product;
        } else {
            return choiceProduct(scanner);
        }
    }

    public boolean checkNumber(int number) {
        for (Company e : listCompany) {
            if (e.getNumber() == number) {
                return true;
            }
        }
        return false;
    }

    public void searchName(Scanner scanner) {
        ArrayList<Company> listName = new ArrayList<>();
        System.out.println("Enter the name of the company you are looking for: ");
        String nameSearch = scanner.nextLine();
        for (Company e : listCompany) {
            if (e.getName().toLowerCase().contains(nameSearch.toLowerCase())) {
                listName.add(e);
            }
        }
        if (!listName.isEmpty()) {
            titleCompany();
            for (Company e : listCompany) {
                e.displayCompany();
            }
        } else {
            System.out.println("Name not found in the list.");
        }
    }

    public Company getByNumber(int idCompany) {
        for (Company e : listCompany) {
            if (e.getNumber() == idCompany) {
                return e;
            }
        }
        return null;
    }
    public void  searchCode(Scanner scanner){
        if (!listCompany.isEmpty()){
            System.out.println("Enter the code you want to search: ");
            String code = scanner.nextLine();
            Company tempt = null;
            for (Company e:listCompany) {
                if (e.getProduct().getCode().equals(code)){
                    tempt = e;
                }
            }
            if (tempt!=null){
                System.out.println("--RESULT--");
                titleCompany();
                tempt.displayCompany();
            }else {
                System.out.println("There are no product codes in the list");
            }
        }else {
            System.out.println("The list is empty.");
        }

    }
}
