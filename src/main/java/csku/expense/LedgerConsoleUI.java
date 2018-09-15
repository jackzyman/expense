package csku.expense;

import java.io.IOException;
import java.util.Scanner;

public class LedgerConsoleUI {

    public void start() throws IOException {

        Ledger ledgerAccount;
        ledgerAccount = new Ledger();
        Logs logs = null;

        Scanner in = new Scanner(System.in);

        while (true) {
            int state = ledgerAccount.getState();
             if (state == Ledger.TRANSACT) {
                System.out.println(">>>>> Balance = " + ledgerAccount.getBalance());
                System.out.print("A=Income, B=Expense, C=History, D=Exit: ");
                String command = in.next();
                if (command.equalsIgnoreCase("A")) {
                    System.out.print("Income: ");
                    double amount = in.nextDouble();
                    System.out.print("Description: ");
                    in.useDelimiter("\n");
                    String description = in.next();
                    ledgerAccount.income(amount, description);
                }
                else if (command.equalsIgnoreCase("B")) {
                    System.out.print("Expense: ");
                    double amount = in.nextDouble();
                    System.out.print("Description: ");
                    in.useDelimiter("\n");
                    String description = in.next();
                    try {
                        ledgerAccount.expense(amount, description);
                    } catch (OverExpenseException e) {
                        System.out.print(e.getMessage());
                    }
                }
                else if (command.equalsIgnoreCase("C")) {
                    System.out.println(ledgerAccount.getTotal());
                    logs.logging(ledgerAccount.getTotal());
                }
                else if (command.equalsIgnoreCase("D"))
                    System.exit(0);
                else
                    System.out.println("Illegal input!");
            }
        }

    }
}



