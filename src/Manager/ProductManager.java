package Manager;

import OptionClass.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements CRUD<Product> {

    static int index = 0;
    private ArrayList<Product> listProduct;

    public ProductManager() {
        listProduct = new ArrayList<>();
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }
    public String pathProduct = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Production";

    @Override
    public Product create(Scanner scanner) {
        int number;
        if (listProduct.isEmpty()) {
            number = index + 1;
        } else {
            number = listProduct.get(listProduct.size() - 1).getNumber() + 1;
        }
        System.out.println("Enter product code: ");
        String code = scanner.nextLine();
        if (!checkCode(code)) {
            System.out.println("Enter product name: ");
            String name = scanner.nextLine();
            System.out.println("Enter product type: ");
            String type = scanner.nextLine();
            return new Product(number, code, name,type);
        } else {
            return null;
        }
    }

    @Override
    public void add(Product product) {
        if (product != null) {
            listProduct.add(product);
            titleProduct();
            product.displayProduct();
        } else {
            System.out.println("Product code already exists.");
        }
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
            for (Product e : listProduct) {
                if (e.getNumber() == number) {
                    System.out.println("Enter product code: ");
                    String code = scanner.nextLine();
                    if (!checkCode(code)) {
                        e.setCode(code);
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        if (!name.equals("")){
                            e.setName(name);
                        }
                        System.out.println("Enter product type: ");
                        String type = scanner.nextLine();
                        if (!type.equals("")){
                            e.setType(type);
                        }
                        System.out.println("Update successfully!");
                    }
                }
            }
        } else {
            System.out.println("Does not exist. Please check again!");
        }
    }

    @Override
    public void delete(Scanner scanner) {
        System.out.println("Enter product code: ");
        String code = scanner.nextLine();
        int index = 0;
        if (checkCode(code)) {
            Product tempt = null;
            for (Product e : listProduct) {
                if (e.getCode().equals(code)) {
                    tempt = e;
                    index = e.getNumber();
                    titleProduct();
                    e.displayProduct();
                }
            }
            for (Product e : listProduct) {
                if (e.getNumber() > index) {
                    e.setNumber(e.getNumber() - 1);
                }
            }
            listProduct.remove(tempt);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("There is no code to delete in the list.");
        }
    }

    @Override
    public void displayAll(List<Product> listProduct) {
        if (listProduct != null) {
            System.out.println("List product: ");
            titleProduct();
            for (Product e : listProduct) {
                e.displayProduct();
            }
        } else {
            System.out.println("There are no products in the list.");
        }
    }

    public void searchName(Scanner scanner){
        ArrayList<Product>list = new ArrayList<>();
        System.out.println("Enter the name of the product you want to search for: ");
        String nameSearch = scanner.nextLine();
        for (Product e : listProduct){
            if (e.getName().toLowerCase().contains(nameSearch.toLowerCase())){
                list.add(e);
            }
        }
        if (!list.isEmpty()){
            titleProduct();
            for (Product e : listProduct){
                e.displayProduct();
            }
        }else {
            System.out.println("There are no products in the list.");
        }
    }
    public void searchType(Scanner scanner){
        ArrayList<Product> listType = new ArrayList<>();
        System.out.println("Enter the type of the product you want to search for: ");
        String typeSearch = scanner.nextLine();
        for (Product e : listProduct){
            if (e.getType().toLowerCase().contains(typeSearch.toLowerCase())){
                listType.add(e);
            }
        }
        if (!listType.isEmpty()){
            titleProduct();
            for (Product e : listType){
                e.displayProduct();
            }
        }else {
            System.out.println("There are no products in the list.");
        }
    }
    @Override
    public void outputFile(String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(listProduct);
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
                listProduct = (ArrayList<Product>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkCode(String code) {
        for (Product e : listProduct) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNumber(int number) {
        for (Product e : listProduct) {
            if (e.getNumber() == number) {
                return true;
            }
        }
        return false;
    }

    public void titleProduct() {
        System.out.printf("%-15s%-20s%-20s%s", "NUMBER", "CODE", "PRODUCT NAME","TYPE\n");
    }

    public Product getByNumber(int idProduct){
        for (Product e: listProduct){
            if (e.getNumber()==idProduct){
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        String path = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\Production";
        productManager.inputFile(path);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("MENU VEHICLE:");
                    System.out.println("1. Add new product. ");
                    System.out.println("2. Update product by number. ");
                    System.out.println("3. Delete product by code. ");
                    System.out.println("4. Search product by name. ");
                    System.out.println("5. Search product by name. ");
                    System.out.println("6. Display all listVehicle. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            Product product = productManager.create(scanner);
                            productManager.add(product);
                            productManager.outputFile(path);
                            check = false;
                            break;
                        case 2:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.update(scanner);
                            productManager.outputFile(path);
                            check = false;
                            break;
                        case 3:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.delete(scanner);
                            productManager.outputFile(path);
                            check = false;
                            break;
                        case 4:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.searchName(scanner);
                            check = false;
                            break;
                        case 5:
                            productManager.displayAll(productManager.getListProduct());
                            productManager.searchType(scanner);
                            check = false;
                            break;
                        case 6:
                            productManager.displayAll(productManager.getListProduct());
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
