package OptionClass;

import java.io.Serializable;

public class Product implements Serializable {
    private int number;
    private String code;
    private String name;
    private String type;
    private double price;

    public Product(int number, String code, String name, String type, double price) {
        this.number = number;
        this.code = code;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayProduct(){
        System.out.printf("%-15s%-20s%-30s%-20s%s",number,code,name,type,price+"\n");
    }
}
