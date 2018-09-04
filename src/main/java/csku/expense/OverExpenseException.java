package csku.expense;

public class OverExpenseException extends Exception {
    public OverExpenseException() {}
    public OverExpenseException(String reason) {
        super(reason);
    }
}