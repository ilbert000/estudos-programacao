package Model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CarRental {

    private LocalDateTime start;
    private LocalDateTime finish;

    private Invoice invoice;
    private Vehicle vehicle;

    public CarRental() {
    }

    public CarRental(LocalDateTime start, LocalDateTime finish, Vehicle vehicle) {
        this.start = start;
        this.finish = finish;
        this.vehicle = vehicle;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime setStart(LocalDateTime start) {
        return this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public LocalDateTime setFinish(LocalDateTime finish) {
        return this.finish = finish;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Invoice setInvoice(Invoice invoice) {
        return this.invoice = invoice;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Vehicle setVehicle(Vehicle vehicle) {
        return this.vehicle = vehicle;
    }
}
