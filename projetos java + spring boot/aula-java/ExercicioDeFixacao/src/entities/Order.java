package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.OderStatus;
import entities.OrderItem;

public class Order {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    private Date moment;
    private OderStatus status;

    private Client client;

    private List<OrderItem> items = new ArrayList<OrderItem>();

    public Order(){

    }

    public Order(Date moment, OderStatus status, Client client){
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public Date getDate(){
        return moment;
    }

    public Date setDate(Date moment){
        return this.moment = moment;
    }

    public Client getClient(){
        return client;
    }

    public Client setClient(Client client){
        return this.client = client;
    }

    public OderStatus getStatus(){
        return status;
    }

    public OderStatus setStatus(OderStatus status){
        return this.status = status;
    }

    public void addItem(OrderItem item){
        items.add(item);
    }

    public void removeItem(OrderItem item){
        items.remove(item);
    }

    public double total(){
        double sum = 0.0;

        for(OrderItem it : items){
            sum = sum + it.subTotal();
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order moment: ");
        sb.append(sdf.format(moment) + "\n");
        sb.append("Order status: ");
        sb.append(status + "\n");
        sb.append("Client: ");
        sb.append(client + "\n");
        sb.append("Order items:\n");
        for (OrderItem item : items) {
            sb.append(item + "\n");
        }
        sb.append("Total price: $");
        sb.append(String.format("%.2f", total()));
        return sb.toString();
    }
}
