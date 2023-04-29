package hu.nye.futarfalatok.exception;

public class RestaurantDishNotFound extends RuntimeException{
    public RestaurantDishNotFound(String message) {
        super(message);
    }
}
