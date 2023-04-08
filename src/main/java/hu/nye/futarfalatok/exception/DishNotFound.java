package hu.nye.futarfalatok.exception;

public class DishNotFound extends RuntimeException {
    public DishNotFound(String message) {
        super(message);
    }
}
