package entities;

public abstract class  Payer {

    private Double income;
    private String name;

    public Payer(){

    }

    public Payer(String name, Double income){
        this.name = name;
        this.income = income;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double impostos();
}
