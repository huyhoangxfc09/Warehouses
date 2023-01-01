package Manager;

import OptionClass.Product;
import OptionClass.RegisteredVehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RegisteredVehicleManager implements CRUD<RegisteredVehicle> {
    private ArrayList<RegisteredVehicle> listVehicle;
    static int index = 0;

    public RegisteredVehicleManager() {
        listVehicle = new ArrayList<>();
    }

    public ArrayList<RegisteredVehicle> getListVehicle() {
        return listVehicle;
    }
    public String pathVehicle = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\LicensePlates";

    @Override
    public RegisteredVehicle create(Scanner scanner) {
        int number;
        if (listVehicle.isEmpty()) {
            number = index + 1;
        } else {
            number = listVehicle.get(listVehicle.size() - 1).getNumber() + 1;
        }
        System.out.println("Enter license plates: (Ex : 29B1-12345)");
        String name = scanner.nextLine();
        if (!checkName(name)) {
            return new RegisteredVehicle(number, name);
        } else {
            return null;
        }
    }

    @Override
    public void add(RegisteredVehicle registeredVehicle) {
        if (registeredVehicle != null) {
            listVehicle.add(registeredVehicle);
            titleVehicle();
            registeredVehicle.displayVehicle();
        } else {
            System.out.println("Check the license plate number.");
        }
    }

    @Override
    public void update(Scanner scanner) {
        int number = 0;
        boolean check = true;
        while (check){
            try {
                System.out.println("Enter the number: ");
                number = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter number.");
            }
        }
        if (checkNumber(number)) {
            for (RegisteredVehicle e : listVehicle) {
                if (e.getNumber() == number) {
                    System.out.println("Enter license plates: (Ex : 29B1-12345)");
                    String name = scanner.nextLine();
                    if (!checkName(name)) {
                        e.setName(name);
                        System.out.println("Update successfully!");
                        e.displayVehicle();
                    } else {
                        System.out.println("License plate already exists.");
                        System.out.println("Check the license plate number.");
                    }
                }
            }
        } else {
            System.out.println("Sequence number does not exist.");
        }
    }

    @Override
    public void delete(Scanner scanner) {
        System.out.println("Enter the license plate number to delete: (Ex : 29B1-12345)");
        String name = scanner.nextLine();
        int index = 0;
        if (checkName(name)) {
            RegisteredVehicle tempt = null;
            for (RegisteredVehicle e : listVehicle) {
                if (e.getName().equals(name)) {
                    tempt = e;
                    index = e.getNumber();
                    titleVehicle();
                    e.displayVehicle();
                }
            }
            for (RegisteredVehicle e : listVehicle) {
                if (e.getNumber() > index) {
                    e.setNumber(e.getNumber() - 1);
                }
            }
            listVehicle.remove(tempt);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("There is no license plate to delete in the list.");
        }
    }

    @Override
    public void displayAll(List<RegisteredVehicle> listVehicle) {
        if (listVehicle != null) {
            System.out.println("List license plates: ");
            titleVehicle();
            for (RegisteredVehicle e : listVehicle) {
                e.displayVehicle();
            }
        } else {
            System.out.println("License plate number registered.");
            System.out.println("List license plates haven't element!");
        }
    }

    public void titleVehicle() {
        System.out.printf("%-10s%s", "NUMBER", "LICENSE PLATES\n");
    }

    public boolean checkName(String name) {
        if (!listVehicle.isEmpty()) {
            for (RegisteredVehicle e : listVehicle) {
                if (e.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkNumber(int number) {
        for (RegisteredVehicle e : listVehicle) {
            if (e.getNumber() == number) {
                return true;
            }
        }
        return false;
    }

    public void searchName(Scanner scanner) {
        System.out.println("Enter license plates: (Ex : 29B1-12345)");
        String name = scanner.nextLine();
        if (checkName(name)) {
            for (RegisteredVehicle e : listVehicle) {
                if (e.getName().equals(name)) {
                    e.displayVehicle();
                }
            }
        } else {
            System.out.println("License plate does not exist.");
        }
    }

    @Override
   public void outputFile(String path){
       File file = new File(path);
       try{
           OutputStream os = new FileOutputStream(file);
           ObjectOutputStream oos = new ObjectOutputStream(os);
           oos.writeObject(listVehicle);
           oos.close();
       }catch (IOException e){
           e.printStackTrace();
       }
   }

   @Override
   public void inputFile(String path){
       File file = new File(path);
        try{
            InputStream is =new  FileInputStream(file);
            if( is.available()>0){
                ObjectInputStream ois = new ObjectInputStream(is);
                listVehicle = (ArrayList<RegisteredVehicle>) ois.readObject();
                ois.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
   }
    public RegisteredVehicle getByNumber(int idCar){
        for (RegisteredVehicle e: listVehicle){
            if (e.getNumber()==idCar){
                return e;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegisteredVehicleManager registeredVehicleManager = new RegisteredVehicleManager();
        String path = "C:\\Users\\PC\\OneDrive\\Desktop\\CaseStudyModule2\\Warehouses\\src\\FileSave\\LicensePlates";
        registeredVehicleManager.inputFile(path);
        int choice;
        boolean check = true;
        while (check) {
            try {
                do {
                    System.out.println("MENU VEHICLE:");
                    System.out.println("1. Add new listVehicle. ");
                    System.out.println("2. Update listVehicle by Id. ");
                    System.out.println("3. Delete listVehicle. ");
                    System.out.println("4. Search vehicle variable by name. ");
                    System.out.println("5. Display all listVehicle. ");
                    System.out.println("0. Exit.");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            RegisteredVehicle registeredVehicle = registeredVehicleManager.create(scanner);
                            registeredVehicleManager.add(registeredVehicle);
                            registeredVehicleManager.outputFile(path);
                            check = false;
                            break;
                        case 2:
                            registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
                            registeredVehicleManager.update(scanner);
                            registeredVehicleManager.outputFile(path);
                            check = false;
                            break;
                        case 3:
                            registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
                            registeredVehicleManager.delete(scanner);
                            registeredVehicleManager.outputFile(path);
                            check = false;
                            break;
                        case 4:
                            registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
                            registeredVehicleManager.searchName(scanner);
                            check = false;
                            break;

                        case 5:
                            registeredVehicleManager.displayAll(registeredVehicleManager.getListVehicle());
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
