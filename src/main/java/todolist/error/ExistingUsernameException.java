package todolist.error;

public class ExistingUsernameException extends Exception {
    @Override
    public String getMessage() {
        return "User with this username already exists!";
    }
}
