package by.voluevich.service.exception;

public class LoginNotFoundException extends Exception{
    private String login;

    public LoginNotFoundException(String login) {
        this.login = login;
    }

    public LoginNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "Login name " + login + " - not found.";
    }
}
