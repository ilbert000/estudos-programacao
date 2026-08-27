import entities.Account;

import java.util.Scanner;
import entities.Account;
import entities.BusinessAccount;
import entities.SavingsAccount;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Account acc = new Account(1001, "Alex", 0.0);
        BusinessAccount bcc = new BusinessAccount(1002, "Maria", 0.0, 500.0);

        //UPCASTING

        Account acc1 = bcc;
        Account acc2 = new BusinessAccount(1002, "Joana", 0.0, 5000.0);
        Account acc3 = new SavingsAccount(1002.0, 0,"Joana" , 1000.0);

        //DONWCASTING

        BusinessAccount acc4 = (BusinessAccount) acc2;

        //VERIFICAÇÂO DE DONWCASTING

        if (acc3 instanceof BusinessAccount){
            BusinessAccount acc5 = (BusinessAccount) acc3;
            acc5.loan(100.0);
            System.out.println("Loan!");
        }

        if (acc3 instanceof SavingsAccount){
            SavingsAccount acc5 = (SavingsAccount) acc3;
            acc5.updateBalance();
            System.out.println("Update!");
        }

        Account x = new Account(1003, "alex", 200.0);
        Account y = new SavingsAccount(0.01, 0,"Joana" , 200.0);

        x.withdraw(50.0);
        y.withdraw(50.0);

        System.out.println(x.getBalance());
        System.out.println(y.getBalance());

    }
}