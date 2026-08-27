import entities.Employee;
import entities.OutsourcedEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<Employee>();

        System.out.println("Enter the number of employees: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i ++){
            System.out.println("Employee #" + i + " date: ");
            System.out.println("Outsourced (y/n)? ");
            char ch = sc.next().charAt(0);
            System.out.println("Name: ");
            String name = sc.next();
            System.out.println("Hours: ");
            int hour = sc.nextInt();
            System.out.println("Value per hours: ");
            double valuePerHour = sc.nextDouble();
            if (ch == 'y'){
                System.out.println("Additional charge: ");
                double addtionalCharge = sc.nextDouble();
                list.add(new OutsourcedEmployee(name, hour, valuePerHour, addtionalCharge));
            }
            else {
                list.add(new Employee(name, hour, valuePerHour));
            }
        }

        System.out.println();
        System.out.println("PAYMENTS: ");
        for (Employee emp : list){
            System.out.println(emp.getName() + " - $ " + String.format("%.2f", emp.payment()));
        }
    }
}