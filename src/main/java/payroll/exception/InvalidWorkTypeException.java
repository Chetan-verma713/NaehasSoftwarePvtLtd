package payroll.exception;

public class InvalidWorkTypeException extends Exception{
    public InvalidWorkTypeException() {
        super();
    }
    public InvalidWorkTypeException(String msg) {
        super(msg);
    }
}
