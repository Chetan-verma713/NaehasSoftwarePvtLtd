package payroll.application;

import payroll.exception.InvalidIdException;
import payroll.exception.InvalidNameException;
import payroll.exception.InvalidWagesException;
import payroll.exception.InvalidWorkTypeException;

public class Verification {
    public void verifyName(String name) throws InvalidNameException {
        if (!(name.length() >= 5) || !name.contains(" ")) throw new InvalidNameException("Employee name must have at least one space and cannot be less than 5 characters.");
    }

    public void verifyId(int id) throws InvalidIdException {
        if (id < 0) throw new InvalidIdException("Employee ID must be a positive integer.");
    }

    public void verifyType(char type) throws InvalidWorkTypeException {
        if (!(type == 'T' || type == 'C' || type == 'F')) throw new InvalidWorkTypeException("Work type must be 'T' or 'C' or 'F'.");
    }

    public void verifyWage(double wage) throws InvalidWagesException {
        if (wage < 0) throw new InvalidWagesException("Wage must be positive double.");
    }
}
