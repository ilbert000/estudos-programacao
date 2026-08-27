package entities;

public class OutsourcedEmployee extends Employee{

    private Double addtionalCharge;

    public OutsourcedEmployee(){

    }

    public OutsourcedEmployee(String name, Integer hours, Double valuePerHour, Double addtionalCharge){
        super(name, hours, valuePerHour);
        this.addtionalCharge = addtionalCharge;
    }

    public double getAddtionalCharge(){
        return addtionalCharge;
    }

    public double setAddtionalCharge(Double addtionalCharge){
        return this.addtionalCharge = addtionalCharge;
    }

    @Override
    public final Double payment(){
        return super.payment() + addtionalCharge * 1.1;
    }
}
