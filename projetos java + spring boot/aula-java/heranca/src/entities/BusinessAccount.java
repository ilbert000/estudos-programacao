package entities;

public class BusinessAccount extends Account{

    private double loanLimit;

    public BusinessAccount(){

    }

    public BusinessAccount(Integer number, String holder, Double balance, Double loanLimit){
        super(number, holder, balance);
        this.loanLimit = loanLimit;
    }

    public double getLoanLimit(){
        return loanLimit;
    }

    public double setLoanlimit(Double loanLimit){
        return this.loanLimit = loanLimit;
    }

    public void loan(double amount){
        if (amount <= loanLimit){
            deposit(amount);
        }
    }

    @Override
    public final void withdraw(double amount){
        super.withdraw(amount);
        balance -= 2.0;
    }

}
