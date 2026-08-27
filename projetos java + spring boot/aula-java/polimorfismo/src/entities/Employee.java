package entities;

import java.security.interfaces.DSAPublicKey;
import java.util.Locale;

public class Employee {
    private String name;
    private Integer hours;
    private Double valuePerHour;

    public Employee(){

    }

    public Employee(String name, Integer hours, Double valuePerHour){
        this.hours = hours;
        this.name = name;
        this.valuePerHour = valuePerHour;
    }

    public String getName(){
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }

    public Integer getHours(){
        return hours;
    }

    public Integer setHours(Integer hours){
        return this.hours = hours;
    }

    public Double getValuePerHour(){
        return valuePerHour;
    }

    public Double setValuePerHour(Double valuePerHour){
        return this.valuePerHour = valuePerHour;
    }

    public Double payment(){
        return hours * valuePerHour;
    }
}
