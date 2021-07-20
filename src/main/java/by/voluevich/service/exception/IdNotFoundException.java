package by.voluevich.service.exception;

public class IdNotFoundException extends Exception {
    private int id;

    public IdNotFoundException(int id) {
        this.id = id;
    }

    public IdNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "Object with id (" + id + ") not found.";
    }
}
