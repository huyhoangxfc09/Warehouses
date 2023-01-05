package OptionClass;

import java.io.Serializable;

public class Company implements Serializable {
    private  int number;
    private String name;
    private Product product;

    public Company(int number, String name, Product product) {
        this.number = number;
        this.name = name;
        this.product = product;
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

    public  void displayCompany(){
        System.out.printf("%-10s%-25s%-15s%-30s%-20s%s",number,name,product.getCode(),product.getName(),product.getType(),product.getPrice()+"\n");
    }
}
