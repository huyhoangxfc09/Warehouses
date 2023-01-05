package OptionClass;

import java.io.Serializable;
import java.util.Date;

public class OutputWarehouse implements Serializable {
    private int number;
    private Date date;
    private String code;
    private String name;
    private InputWarehouse inputWarehouse;
    private int quantity;
    private double total;

    public OutputWarehouse(int number, Date date, String code, String name, InputWarehouse inputWarehouse, int quantity,double total) {
        this.number = number;
        this.date = date;
        this.code = code;
        this.name = name;
        this.inputWarehouse = inputWarehouse;
        this.quantity = quantity;
        this.total = total;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public InputWarehouse getInputWarehouse() {
        return inputWarehouse;
    }

    public void setInputWarehouse(InputWarehouse inputWarehouse) {
        this.inputWarehouse = inputWarehouse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public void displayOutput(){
        System.out.printf("%-10s%-30s%-20s%-25s%-25s%-25s%-10s%s",
                number,date,code,name,inputWarehouse.getCompany().getProduct().getName(),inputWarehouse.getCode(),quantity,total+"\n");
    }
}
