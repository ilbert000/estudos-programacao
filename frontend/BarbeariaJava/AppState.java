import java.util.ArrayList;
import java.util.List;

public class AppState {
    private List<Service> selectedServices = new ArrayList<>();
    private String paymentMethod;
    private CustomerData customerData = new CustomerData();
    
    public List<Service> getSelectedServices() { return selectedServices; }
    public void setSelectedServices(List<Service> selectedServices) { this.selectedServices = selectedServices; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public CustomerData getCustomerData() { return customerData; }
    public void setCustomerData(CustomerData customerData) { this.customerData = customerData; }
}