package entities;

public class Product {

    private String name;
    private Double price;

    public Product(){
    }

    public Product(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public double setPrice(Double price){
        return this.price = price;
    }

    public String getName(){
        return name;
    }

    public String setPrice(String name){
        return this.name = name;
    }
}
