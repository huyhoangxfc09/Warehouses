package OptionClass;

import java.io.Serializable;

public class Company implements Serializable {
    private  int number;
    private String name;
    private Product product;
    private RegisteredVehicle licensePlates;

    public Company(int number, String name, Product product, RegisteredVehicle licensePlates) {
        this.number = number;
        this.name = name;
        this.product = product;
        this.licensePlates = licensePlates;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public RegisteredVehicle getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(RegisteredVehicle licensePlates) {
        this.licensePlates = licensePlates;
    }
    public  void displayCompany(){
        System.out.printf("%-10s%-25s%-15s%-30s%-20s%s",number,name,product.getCode(),product.getName(),product.getType(),licensePlates.getName()+"\n");
    }
}
