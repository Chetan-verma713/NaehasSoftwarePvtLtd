package payroll.application;

interface Pay {
    double calculateTax(double wage, char type);
}

public class Payroll implements Pay{
    @Override
    public double calculateTax(double wage, char type) {
        double taxes;
        if (type == 'T') {
            taxes = (wage * 15)/100;
        } else if (type == 'C') {
            taxes = (wage * 18)/100;
        } else {
            taxes = (wage * 30)/100;
        }
        return wage-taxes;
    }
}
