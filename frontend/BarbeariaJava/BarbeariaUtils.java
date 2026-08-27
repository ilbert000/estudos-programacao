import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BarbeariaUtils {
    
    public static void generateBusinessHoursReport() {
        System.out.println("=== HORÁRIOS DE FUNCIONAMENTO ===");
        BarbeariaApp.getBusinessHours().forEach((day, hours) -> {
            String dayName = getDayNamePortuguese(day);
            String schedule = hours.isClosed() ? "Fechado" : 
                hours.getOpen().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + 
                hours.getClose().format(DateTimeFormatter.ofPattern("HH:mm"));
            
            System.out.println(dayName + ": " + schedule);
        });
    }
    
    public static void generateServicesReport() {
        System.out.println("\n=== SERVIÇOS DISPONÍVEIS ===");
        List<Service> services = BarbeariaApp.getServices();
        services.forEach(service -> {
            System.out.println(service.getId() + ". " + service.getName() + 
                             " - R$ " + String.format("%.2f", service.getPrice()) +
                             " - " + service.getDescription());
        });
    }
    
    public static String formatDatePortuguese(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    private static String getDayNamePortuguese(java.time.DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case SUNDAY: return "Domingo";
            case MONDAY: return "Segunda-feira";
            case TUESDAY: return "Terça-feira";
            case WEDNESDAY: return "Quarta-feira";
            case THURSDAY: return "Quinta-feira";
            case FRIDAY: return "Sexta-feira";
            case SATURDAY: return "Sábado";
            default: return "";
        }
    }
}