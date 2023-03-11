package payroll.exception;

public class InvalidWagesException extends Exception{
    public InvalidWagesException() {
        super();
    }
    public InvalidWagesException(String msg) {
        super(msg);
    }
}
