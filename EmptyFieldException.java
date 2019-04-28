public class EmptyFieldException extends Exception {
    public EmptyFieldException(String message) {
        // This sintax is to acces the constructor of the parent
        super(message);
    }
}