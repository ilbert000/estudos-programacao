package entities;

public class Individual extends Payer{

    private Double health;

    public Individual(){
        super();
    }

    public Individual(String name, Double income, Double health){
        super(name, income);
        this.health = health;
    }

    public Double getHealth() {
        return health;
    }

    public Double setHealth(Double health) {
        return this.health = health;
    }

    @Override
    public double impostos(){
        double basicTax;
        if (getIncome() < 20000.0) {
            basicTax = getIncome() * 0.15;
        }
        else {
            basicTax = getIncome() * 0.25;
        }
        basicTax -= getHealth() * 0.5;
        if (basicTax < 0.0) {
            basicTax = 0.0;
        }
        return basicTax;
    }
}
