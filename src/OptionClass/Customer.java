package OptionClass;

import java.io.Serializable;

public class Customer implements Serializable {
    private int number;
    private String name;
    private String citizenID;

    private String company;
    private String address;
    private String phone;

    public Customer(int number, String name, String citizenID, String company, String address,String phone) {
        this.number = number;
        this.name = name;
        this.citizenID = citizenID;
        this.company = company;
        this.address = address;
        this.phone = phone;
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

    public String getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(String citizenID) {
        this.citizenID = citizenID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void displayCustomer(){
        System.out.printf("%-10s%-30s%-30s%-30s%-70s%s",number,name,citizenID,company,address,phone+"\n");
    }
}
