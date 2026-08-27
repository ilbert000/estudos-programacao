public class BusinessStatus {
    private boolean isOpen;
    private String message;
    
    public BusinessStatus(boolean isOpen, String message) {
        this.isOpen = isOpen;
        this.message = message;
    }
    
    public boolean isOpen() { return isOpen; }
    public String getMessage() { return message; }
}