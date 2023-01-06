package OptionClass;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    private int number;
    private Date date;
    private Customer customer;
    private OutputWarehouse outputWarehouse;

    public Bill(int number, Date date, Customer customer, OutputWarehouse outputWarehouse) {
        this.number = number;
        this.date = date;
        this.customer = customer;
        this.outputWarehouse = outputWarehouse;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OutputWarehouse getOutputWarehouse() {
        return outputWarehouse;
    }

    public void setOutputWarehouse(OutputWarehouse outputWarehouse) {
        this.outputWarehouse = outputWarehouse;
    }

    public void displayBill(){
        System.out.printf("%-10s%-30s%-20s%-25s%-20s%-20s%-30s%-10s%s",
                number,date,outputWarehouse.getCode(),customer.getName(),customer.getCitizenID(),customer.getCompany(),
                outputWarehouse.getInputWarehouse().getCompany().getProduct(),outputWarehouse.getQuantity(),outputWarehouse.getTotal()+"\n");
    }
}
