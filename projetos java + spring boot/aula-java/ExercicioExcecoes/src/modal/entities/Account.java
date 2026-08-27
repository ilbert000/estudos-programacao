package modal.entities;

import modal.exception.BusinessException;

public class Account {

    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Account(){

    }

    public Account(Integer number, String holder, Double balance, Double withdrawLimit){
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public Double getBalance() {
        return balance;
    }

    public Double setBalance(Double balance) {
        return this.balance = balance;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer setNumber(Integer number) {
        return this.number = number;
    }

    public Double getWithdrawLimit() {
        return withdrawLimit;
    }

    public Double setWithdrawLimit(Double withdrawLimit) {
        return this.withdrawLimit = withdrawLimit;
    }

    public String getHolder() {
        return holder;
    }

    public String setHolder(String holder) {
        return this.holder = holder;
    }

    public void deposit(Double amount){
        validationWithdrawLimit(amount);
        balance += amount;
    }

    public void withdraw(Double amount){
        validationWithdrawLimit(amount);
        balance -= amount;
    }

    public void validationWithdrawLimit(Double amount){
        if (amount > withdrawLimit){
            throw new BusinessException("Erro de saque: A quantia excede o limite de saque");
        }
        if (amount > balance) {
            throw new BusinessException("Erro de saque: Saldo insuficiente");
        }


    }
}
