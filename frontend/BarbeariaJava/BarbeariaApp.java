import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BarbeariaApp {
    private static final Map<DayOfWeek, BusinessHours> businessHours = new EnumMap<>(DayOfWeek.class);
    private static final List<Service> services = Arrays.asList(
        new Service(1, "Corte Social", 30.00, "Corte clássico e profissional", "fas fa-cut"),
        new Service(2, "Corte Degradê", 35.00, "Corte moderno com degradê", "fas fa-layer-group"),
        new Service(3, "Corte + Barba", 50.00, "Corte completo com barba", "fas fa-user-check"),
        new Service(4, "Barba Completa", 25.00, "Aparar e modelar a barba", "fas fa-air-freshener"),
        new Service(5, "Sobrancelha", 15.00, "Design e modelagem de sobrancelhas", "fas fa-eye"),
        new Service(6, "Pigmentação", 40.00, "Técnica de realce da barba", "fas fa-palette")
    );
    
    private AppState state = new AppState();
    private Scanner scanner = new Scanner(System.in);
    
    static {
        businessHours.put(DayOfWeek.MONDAY, new BusinessHours(LocalTime.of(6, 0), LocalTime.of(17, 0)));
        businessHours.put(DayOfWeek.TUESDAY, new BusinessHours(LocalTime.of(6, 0), LocalTime.of(17, 0)));
        businessHours.put(DayOfWeek.WEDNESDAY, new BusinessHours(LocalTime.of(6, 0), LocalTime.of(17, 0)));
        businessHours.put(DayOfWeek.THURSDAY, new BusinessHours(LocalTime.of(6, 0), LocalTime.of(17, 0)));
        businessHours.put(DayOfWeek.FRIDAY, new BusinessHours());
        businessHours.put(DayOfWeek.SATURDAY, new BusinessHours());
        businessHours.put(DayOfWeek.SUNDAY, new BusinessHours(LocalTime.of(8, 0), LocalTime.of(14, 0)));
    }
    
    public BusinessStatus checkBusinessStatus() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek currentDay = now.getDayOfWeek();
        LocalTime currentTime = now.toLocalTime();
        
        BusinessHours todayHours = businessHours.get(currentDay);
        
        if (todayHours.isClosed()) {
            return new BusinessStatus(false, "Fechado hoje");
        }
        
        if (!currentTime.isBefore(todayHours.getOpen()) && !currentTime.isAfter(todayHours.getClose())) {
            return new BusinessStatus(true, "Aberto - Fecha às " + formatTime(todayHours.getClose()));
        } else if (currentTime.isBefore(todayHours.getOpen())) {
            return new BusinessStatus(false, "Fechado - Abre " + getNextOpenDay(now));
        } else {
            return new BusinessStatus(false, "Fechado - Abre " + getNextOpenDay(now));
        }
    }
    
    private String getNextOpenDay(LocalDateTime currentDate) {
        LocalDateTime nextDay = currentDate.plusDays(1);
        
        for (int i = 0; i < 7; i++) {
            DayOfWeek dayOfWeek = nextDay.getDayOfWeek();
            BusinessHours dayHours = businessHours.get(dayOfWeek);
            
            if (!dayHours.isClosed()) {
                String dayName = getDayNamePortuguese(dayOfWeek);
                return dayName + " às " + formatTime(dayHours.getOpen());
            }
            
            nextDay = nextDay.plusDays(1);
        }
        
        return "em breve";
    }
    
    private String getDayNamePortuguese(DayOfWeek dayOfWeek) {
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
    
    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public void toggleServiceSelection(int serviceId) {
        Optional<Service> serviceOpt = services.stream()
                .filter(s -> s.getId() == serviceId)
                .findFirst();
        
        if (serviceOpt.isPresent()) {
            Service service = serviceOpt.get();
            Optional<Service> existingService = state.getSelectedServices().stream()
                    .filter(s -> s.getId() == serviceId)
                    .findFirst();
            
            if (existingService.isPresent()) {
                state.getSelectedServices().removeIf(s -> s.getId() == serviceId);
                System.out.println("✓ Serviço removido: " + service.getName());
            } else {
                state.getSelectedServices().add(service);
                System.out.println("✓ Serviço adicionado: " + service.getName());
            }
        } else {
            System.out.println("❌ Serviço não encontrado!");
        }
    }
    
    public double calculateTotal() {
        return state.getSelectedServices().stream()
                .mapToDouble(Service::getPrice)
                .sum();
    }
    
    public boolean isValidAppointmentDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
    
    public String formatPhoneNumber(String phone) {
        String digits = phone.replaceAll("\\D", "");
        
        if (digits.length() <= 11) {
            if (digits.length() <= 2) {
                return "(" + digits;
            } else if (digits.length() <= 6) {
                return "(" + digits.substring(0, 2) + ") " + digits.substring(2);
            } else if (digits.length() <= 10) {
                return "(" + digits.substring(0, 2) + ") " + digits.substring(2, 6) + "-" + digits.substring(6);
            } else {
                return "(" + digits.substring(0, 2) + ") " + digits.substring(2, 7) + "-" + digits.substring(7);
            }
        }
        
        return phone;
    }
    
    public boolean makeAppointment(String nome, LocalDate data, LocalTime hora, String telefone) {
        if (nome == null || nome.trim().isEmpty() || data == null || hora == null || telefone == null || telefone.trim().isEmpty()) {
            System.out.println("❌ Por favor, preencha todos os campos!");
            return false;
        }
        
        if (!isValidAppointmentDate(data)) {
            System.out.println("❌ Não é possível agendar para sextas ou sábados!");
            return false;
        }
        
        if (state.getSelectedServices().isEmpty()) {
            System.out.println("❌ Selecione pelo menos um serviço!");
            return false;
        }
        
        if (state.getPaymentMethod() == null) {
            System.out.println("❌ Selecione uma forma de pagamento!");
            return false;
        }
        
        CustomerData customerData = new CustomerData();
        customerData.setNome(nome);
        customerData.setData(data);
        customerData.setHora(hora);
        customerData.setTelefone(telefone);
        state.setCustomerData(customerData);
        
        return true;
    }
    
    public void showMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          BARBEARIA - SISTEMA DE AGENDAMENTO");
        System.out.println("=".repeat(50));
        
        BusinessStatus status = checkBusinessStatus();
        System.out.println("Status: " + (status.isOpen() ? "🟢 ABERTO" : "🔴 FECHADO"));
        System.out.println("Mensagem: " + status.getMessage());
        System.out.println("-".repeat(50));
        
        System.out.println("1. Ver horários de funcionamento");
        System.out.println("2. Ver serviços disponíveis");
        System.out.println("3. Selecionar serviços");
        System.out.println("4. Ver carrinho");
        System.out.println("5. Fazer agendamento");
        System.out.println("6. Sair");
        System.out.println("-".repeat(50));
        System.out.print("Escolha uma opção: ");
    }
    
    public void run() {
        boolean running = true;
        
        while (running) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (option) {
                case 1:
                    BarbeariaUtils.generateBusinessHoursReport();
                    break;
                case 2:
                    BarbeariaUtils.generateServicesReport();
                    break;
                case 3:
                    selectServices();
                    break;
                case 4:
                    showCart();
                    break;
                case 5:
                    makeAppointmentInteractive();
                    break;
                case 6:
                    running = false;
                    System.out.println("Obrigado por usar nosso sistema!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
    }
    
    private void selectServices() {
        BarbeariaUtils.generateServicesReport();
        System.out.print("\nDigite o ID do serviço para adicionar/remover (0 para voltar): ");
        int serviceId = scanner.nextInt();
        scanner.nextLine();
        
        if (serviceId != 0) {
            toggleServiceSelection(serviceId);
        }
    }
    
    private void showCart() {
        System.out.println("\n=== SEU CARRINHO ===");
        if (state.getSelectedServices().isEmpty()) {
            System.out.println("Carrinho vazio");
        } else {
            state.getSelectedServices().forEach(service -> {
                System.out.println("• " + service.getName() + " - R$ " + String.format("%.2f", service.getPrice()));
            });
            System.out.println("Total: R$ " + String.format("%.2f", calculateTotal()));
        }
    }
    
    private void makeAppointmentInteractive() {
        System.out.println("\n=== FAZER AGENDAMENTO ===");
        
        // Verificar se há serviços selecionados
        if (state.getSelectedServices().isEmpty()) {
            System.out.println("❌ Selecione serviços primeiro!");
            return;
        }
        
        // Coletar dados do cliente
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Data (DD/MM/AAAA): ");
        String dataStr = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        System.out.print("Horário (HH:MM): ");
        String horaStr = scanner.nextLine();
        LocalTime hora = LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm"));
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        // Selecionar forma de pagamento
        System.out.println("\nForma de pagamento:");
        System.out.println("1. Dinheiro");
        System.out.println("2. Cartão");
        System.out.println("3. PIX");
        System.out.print("Escolha: ");
        int pagamento = scanner.nextInt();
        scanner.nextLine();
        
        switch (pagamento) {
            case 1: state.setPaymentMethod("Dinheiro"); break;
            case 2: state.setPaymentMethod("Cartão"); break;
            case 3: state.setPaymentMethod("PIX"); break;
            default: state.setPaymentMethod("Dinheiro");
        }
        
        // Fazer agendamento
        if (makeAppointment(nome, data, hora, telefone)) {
            System.out.println("\n✅ AGENDAMENTO CONFIRMADO!");
            System.out.println("Cliente: " + nome);
            System.out.println("Data: " + BarbeariaUtils.formatDatePortuguese(data));
            System.out.println("Horário: " + hora.format(DateTimeFormatter.ofPattern("HH:mm")));
            System.out.println("Telefone: " + formatPhoneNumber(telefone));
            System.out.println("Serviços: " + state.getSelectedServices().stream()
                    .map(Service::getName)
                    .collect(Collectors.joining(", ")));
            System.out.println("Pagamento: " + state.getPaymentMethod());
            System.out.println("Total: R$ " + String.format("%.2f", calculateTotal()));
        }
    }
    
    public static List<Service> getServices() {
        return new ArrayList<>(services);
    }
    
    public static Map<DayOfWeek, BusinessHours> getBusinessHours() {
        return new EnumMap<>(businessHours);
    }
    
    public AppState getState() {
        return state;
    }
    
    public static void main(String[] args) {
        BarbeariaApp app = new BarbeariaApp();
        app.run();
    }
}