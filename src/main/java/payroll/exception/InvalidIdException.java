package payroll.exception;

public class InvalidIdException extends Exception{
    public InvalidIdException() {
        super();
    }
    public InvalidIdException(String msg) {
        super(msg);
    }
}
