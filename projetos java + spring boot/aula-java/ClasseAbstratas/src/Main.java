import entities.Shape;
import enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import entities.Rectangle;
import entities.Circle;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Shape> list = new ArrayList<>();

        System.out.print("Enter the number of shapes: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++){
            System.out.println("Shape #" + i + "data: ");
            System.out.print("Rectangle or Circle (r/c)? ");
            char s = sc.next().charAt(0);
            System.out.println("Color (BLACK/RED/BLUE): ");
            Color color = Color.valueOf(sc.next());

            if (s == 'r'){
                System.out.print("Width: ");
                double width = sc.nextDouble();
                System.out.print("Height: ");
                double height = sc.nextDouble();
                list.add(new Rectangle(color, height, width));
            } else if (s == 'c') {
                System.out.print("Radius: ");
                double radius = sc.nextDouble();
                list.add(new Circle(color, radius));
            } else {
                System.out.println("error...");
            }
        }

        System.out.println();
        System.out.println("SHAPE AREAS: ");
        for (Shape shape : list) {
            System.out.println(String.format("%.2f", shape.area()));
        }

        sc.close();
    }
}