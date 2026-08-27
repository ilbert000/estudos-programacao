import java.time.LocalTime;

public class BusinessHours {
    private LocalTime open;
    private LocalTime close;
    
    public BusinessHours(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
    }
    
    public BusinessHours() {
        this.open = null;
        this.close = null;
    }
    
    public LocalTime getOpen() { return open; }
    public void setOpen(LocalTime open) { this.open = open; }
    public LocalTime getClose() { return close; }
    public void setClose(LocalTime close) { this.close = close; }
    
    public boolean isClosed() {
        return open == null || close == null;
    }
}