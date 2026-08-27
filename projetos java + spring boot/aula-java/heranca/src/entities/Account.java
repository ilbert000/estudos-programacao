package entities;

public class Account {

    private Integer number;
    private String holder;
    public Double balance;

    public Account(){

    }

    public Account(Integer number, String holder, Double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }

    public Integer getNumber(){
        return number;
    }

    public Integer setNumber(Integer number){
        return this.number = number;
    }

    public String getHolder(){
        return holder;
    }

    public double setHolder(Double balance) {
        return this.balance = balance;
    }

    public Double getBalance(){
        return balance;
    }

    public Double setBalance(Double balance){
        return this.balance = balance;
    }

    public void withdraw(double amount){
        balance -= amount - 5.00;
    }

    public void deposit(double amount){
        balance += amount;
    }

}

