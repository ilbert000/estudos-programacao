package entities;

public class Company extends Payer {

    private int employees;

    public Company() {
        super();
    }

    public Company(String name, Double income, int employees) {
        super(name, income);
        this.employees = employees;
    }

    public int getEmployees() {
        return employees;
    }

    public int setEmployees(int employees) {
        return this.employees = employees;
    }

    @Override
    public double impostos() {
        if (employees > 10) {
            return getIncome() * 0.14;
        }
        else {
            return getIncome() * 0.16;
        }
    }
}
