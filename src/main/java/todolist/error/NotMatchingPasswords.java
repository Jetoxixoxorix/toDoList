package todolist.error;

public class NotMatchingPasswords extends Exception {
    @Override
    public String getMessage() {
        return  "Passwords aren't the same!";
    }
}
