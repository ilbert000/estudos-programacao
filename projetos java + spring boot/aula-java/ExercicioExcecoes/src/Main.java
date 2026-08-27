import java.util.Scanner;
import modal.entities.Account;
import modal.exception.BusinessException;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter account data");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Holder: ");
        String holder = sc.next();
        System.out.print("Initial balance: ");
        double balance = sc.nextDouble();
        System.out.print("Withdraw limit: ");
        double limit = sc.nextDouble();

        Account acc = new Account(number, holder, balance, limit);

        System.out.println();

        System.out.print("Enter amount for withdraw: ");
        double amount = sc.nextDouble();
        try {
            acc.withdraw(amount);
            System.out.print("New balance: " + acc.getBalance());
        }
        catch (BusinessException e){
            System.out.println(e.getMessage());
        }


        sc.close();
    }
}