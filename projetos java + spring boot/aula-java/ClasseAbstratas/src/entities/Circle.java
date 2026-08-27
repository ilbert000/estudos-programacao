package entities;

import enums.Color;

public class Circle extends Shape{

    private Double radius;

    public Circle(){
        super();
    }

    public Circle(Color color, Double radius){
        super(color);
        this.radius = radius;
    }

    public Double getRadius(){
        return radius;
    }

    public Double setRadius(Double radius){
        return this.radius = radius;
    }

    @Override
    public double area(){
        return Math.PI * radius * radius;
    }
}
