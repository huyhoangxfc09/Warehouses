package OptionClass;

import java.io.Serializable;

public class Product implements Serializable {
    private int number;
    private String code;
    private String name;
    public String type;

    public Product(int number, String code, String name, String type) {
        this.number = number;
        this.code = code;
        this.name = name;
        this.type = type;
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

    public void displayProduct(){
        System.out.printf("%-15s%-20s%-20s%s",number,code,name,type+"\n");
    }
}
