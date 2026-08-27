package entities;

public class SavingsAccount extends Account{

    private Double interestRate;

    public SavingsAccount(){
        super();
    }

    public SavingsAccount(Double interestRate, Integer number, String holder, Double balance){
        super(number, holder, balance);
        this.interestRate = interestRate;
    }

    public Double getInterestRate(){
        return interestRate;
    }

    public Double setInterestRate(Double interestRate){
        return this.interestRate = interestRate;
    }

    public void updateBalance() {
        balance += balance * interestRate;
    }

    @Override
    public final void withdraw(double amount){
        balance -= amount;
    }

}
