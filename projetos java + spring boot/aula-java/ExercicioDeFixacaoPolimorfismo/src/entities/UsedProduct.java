package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UsedProduct extends Product{

    private LocalDate manufactureDate;

    public UsedProduct(Double price, String name, LocalDate manufactureDate){
        super(name, price);
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getManufactureDate(){
        return manufactureDate;
    }

    public LocalDate setManufactureDate(LocalDate manufactureDate){
        return this.manufactureDate = manufactureDate;
    }

    @Override
    public String priceTag() {
        return getName()
                + " (used) $ "
                + String.format("%.2f", getPrice())
                + " (Manufacture date: "
                + manufactureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ")";
    }
}
