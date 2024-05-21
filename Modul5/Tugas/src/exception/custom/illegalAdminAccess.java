package exception.custom;

public class illegalAdminAccess extends Exception {
    public illegalAdminAccess() {
        super("Illegal admin access detected.");
    }

    public illegalAdminAccess(String message) {
        super(message);
    }

}
