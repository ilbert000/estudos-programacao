import entities.Company;
import entities.Individual;
import entities.Payer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Payer> list = new ArrayList<>();

        System.out.print("Enter the number of tax payer: ");
        int n = sc.nextInt();

        for (int i = 1; i < n; i++){
            System.out.println("Tax payer #" + i + " data");
            System.out.print("Individual or company (i | n)? ");
            char res = sc.next().charAt(0);
            System.out.print("Name: ");
            String name = sc.next();
            System.out.print("Anual income: ");
            double income = sc.nextDouble();

            if (res == 'c'){
                System.out.print("Number of employees: ");
                int employees = sc.nextInt();
                list.add(new Company(name, income, employees));
            } else if (res == 'i') {
                System.out.print("Health expenditures: ");
                double health = sc.nextDouble();
                list.add(new Individual(name, income, health));
            } else {
                System.out.println("error");
            }
        }

        System.out.println();
        System.out.println("TAZES PAID");

        for (Payer tp : list) {
            System.out.println(tp.getName() + ": $ " + String.format("%.2f", tp.impostos()));
        }

        System.out.println();
        double sum = 0.0;
        for (Payer tp : list) {
            sum += tp.impostos();
        }
        System.out.println("TOTAL TAXES: $ " + String.format("%.2f", sum));

        sc.close();
    }
}