package OptionClass;

public class RegisteredVehicle {
    private int number;
    private String name;

    public RegisteredVehicle(int number, String name) {
        this.number = number;
        this.name = name;
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
    public  void displayVehicle(){
        System.out.printf("%-10s%s",number, name +"\n");
    }
}
