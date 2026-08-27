package entities;

public class OrderItem {

    private Integer quantity;
    private Double price;
    private Product product;

    public OrderItem(){
    }

    public OrderItem(Integer quantity, Double price, Product product){
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public Integer setQuantity(Integer quantity){
        return this.quantity = quantity;
    }

    public double getPrice(){
        return price;
    }

    public double setPrice(Double price){
        return this.price = price;
    }

    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public double subTotal(){
        return price * quantity;
    }

    @Override
    public String toString(){
        return getProduct().getName()
                + ", $"
                + String.format("%.2f", price)
                + ", Quantity: "
                + quantity
                + ", Subtotal: $"
                + String.format("%.2f", subTotal());
    }
}
