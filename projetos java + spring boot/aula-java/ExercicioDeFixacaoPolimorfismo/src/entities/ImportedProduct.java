package entities;

public class ImportedProduct extends Product{

    Product p = new Product();

    private Double customsFee;

    public ImportedProduct(){

    }

    public ImportedProduct(Double customsFee, Double price, String name){
        super(name, price);
        this.customsFee = customsFee;
    }

    public Double getCustomsFee(){
        return customsFee;
    }

    public Double setCustomsFee(Double customsFee){
        return this.customsFee = customsFee;
    }

    public final Double totalPrice(){
        return p.getPrice().doubleValue() + customsFee;
    }

    @Override
    public String priceTag() {
        return getName()
                + " $ "
                + String.format("%.2f", totalPrice())
                + " (Customs fee: $ "
                + String.format("%.2f", customsFee)
                + ")";
    }

}
