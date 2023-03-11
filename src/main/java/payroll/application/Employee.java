package payroll.application;

public class Employee {
    private String name;
    private int id;
    private char type;
    private double wage;
    public Employee(String name, int id, char type, double wage){
        setName(name);
        setId(id);
        setType(type);
        setWage(wage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
