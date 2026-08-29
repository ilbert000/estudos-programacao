package Model.entities;

public class Invoice {

    private Double basicPayment;
    private Double tax;

    public Invoice(){

    }

    public Invoice(Double tax, Double basicPayment){
        this.basicPayment = basicPayment;
        this.tax = tax;
    }

    public Double getBasicPayment(){
        return basicPayment;
    }

    public Double setBasicPayment(Double basicPayment) {
        return this.basicPayment = basicPayment;
    }

    public Double getTax(){
        return tax;
    }

    public Double setTax(Double tax) {
        return this.tax = tax;
    }

    public Double getTotalPayment(){
        return getTotalPayment() + getTax();
    }
}
