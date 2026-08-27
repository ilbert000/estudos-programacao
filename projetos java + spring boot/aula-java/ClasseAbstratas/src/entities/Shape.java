package entities;

import enums.Color;

public abstract class Shape {

    private Color color;

    public  Shape(){

    }

    public Shape(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public Color setColor(Color color){
        return this.color = color;
    }

    public abstract double area();
}
