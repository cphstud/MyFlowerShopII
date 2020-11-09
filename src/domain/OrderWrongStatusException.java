package domain;

public class OrderWrongStatusException extends Exception{
    public OrderWrongStatusException(String msg) {
        super(msg);
    }
}
