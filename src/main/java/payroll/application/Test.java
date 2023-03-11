package payroll.application;

import payroll.exception.InvalidNameException;
import payroll.exception.InvalidWagesException;
import payroll.exception.InvalidIdException;
import payroll.exception.InvalidWorkTypeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Test {
    static ArrayList<Employee> employees = new ArrayList<>();
    Scanner scan;
    static int countTemporary = 0;
    static int countContract = 0;
    static int countFullTime = 0;
    Payroll payroll = new Payroll();
    Employee employee;
    Verification verifier = new Verification();

    void solution() {
        while (true) {
            try {
                registerNewEmployee();
            } catch (Exception e) {
                if (e.getMessage() == null) System.err.println("Error : " + "Enter a valid input.");
                else System.err.println("Error : " + e.getMessage());
            }
            System.out.println();
        }
    }
    private void registerNewEmployee() throws Exception {
        scan = new Scanner(System.in);
        System.out.print("Do you want to register another employee (Y/N)? :   ");
        char ans = scan.next().toUpperCase().charAt(0);
        scan.nextLine();
        if (ans == 'Y') {
            accept();
        } else if (ans == 'N') {
            reject();
        } else {
            throw new Exception("Enter either Y or N");
        }
    }
    private void accept(){
        String name = getInputName();
        int id = getInputId();
        char type = getInputType();
        double wage = getInputWage(type);
        employee = new Employee(name, id, type, wage);
        employees.add(employee);
        System.out.println("Employee’s wage after tax: $" + payroll.calculateTax(employee.getWage(), employee.getType()) + "\n");
    }

    private void reject() {
        Iterator<Employee> iterator = employees.iterator();
        double totalWageBeforeTax = 0;
        double totalWageAfterTax = 0;

        while (iterator.hasNext()) {
            Employee employee = iterator.next();

            System.out.println("Employee name : " + employee.getName());
            System.out.println("Employee ID : " + employee.getId());
            System.out.println("Employee work type : " + employee.getType());

            countType(employee.getType());

            System.out.println("Employee wage before tax $: " + employee.getWage());

            totalWageBeforeTax += employee.getWage();
            double wageAfterTax = payroll.calculateTax(employee.getWage(), employee.getType());

            System.out.println("Employee wage after tax $: " + wageAfterTax);

            totalWageAfterTax += wageAfterTax;
            System.out.println();
        }

        System.out.println("Total employees : " + employees.size());
        System.out.println("Work Types : (" + countTemporary + ") Temporary, (" + countContract + ") Contract, (" + countFullTime + ") Full-Time");
        System.out.println("Total wages before tax : $" + totalWageBeforeTax);
        System.out.println("Total wages after tax : $" + totalWageAfterTax);

        System.exit(0);
    }

    private String getInputName() {
        String name = "";
        boolean flag = false;
        while (!flag) {
            try {
                scan = new Scanner(System.in);
                System.out.print("\nPlease enter the employee’s name? : ");
                name = scan.nextLine();
                verifier.verifyName(name);
                flag = true;
            } catch (InvalidNameException e) {
                System.err.println("Error : " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error : " + "Enter a valid input.");
            }
        }
        return name;
    }

    private int getInputId() {
        int id = -1;
        boolean flag = false;
        while (!flag) {
            try {
                scan = new Scanner(System.in);
                System.out.print("\nPlease enter the employee’s ID? : ");
                id = scan.nextInt();
                verifier.verifyId(id);
                flag = true;
            } catch (InvalidIdException e) {
                System.err.println("Error : " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error : " + "Enter a valid input.");
            }
        }
        return id;
    }

    private char getInputType() {
        char type = '0';
        boolean flag = false;
        while (!flag) {
            try {
                scan = new Scanner(System.in);
                System.out.print("\nPlease enter the employee’s work type? :  ");
                type = scan.next().toUpperCase().charAt(0);
                scan.nextLine();
                verifier.verifyType(type);
                flag = true;
            } catch (InvalidWorkTypeException e) {
                System.err.println("Error : " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error : " + "Enter a valid input.");
            }
        }
        return type;
    }
    private double getInputWage(char type) {
        double wage = -1;
        boolean flag = false;
        while (!flag) {
            try {
                scan = new Scanner(System.in);
                System.out.print("\nPlease enter the employee’s wage? : ");
                wage = scan.nextDouble();
                verifier.verifyWage(wage);
                verify(wage, type);
                flag = true;
            } catch (InvalidWagesException e) {
                System.err.println("Error : " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error : " + "Enter a valid input.");
            }
        }
        return wage;
    }

    private void verify(double wage, char type) throws InvalidWagesException {
        if (type == 'T' && !(wage < 90 && wage >= 0)) throw new InvalidWagesException("The hourly pay cannot exceed 90.00 but can be 0");
        else if (type == 'C' && !(wage < 3500 && wage > 1000)) throw new InvalidWagesException("The bi-weekly pay cannot be below 1000.00 or more than 3500.00 ");
        else if (type == 'F' && wage < 30000) throw new InvalidWagesException("The monthly pay cannot be less than 3000.00 ");
    }

    private void countType(char type) {
        if (type == 'C') countContract++;
        else if (type == 'T') countTemporary++;
        else countFullTime++;
    }
}
