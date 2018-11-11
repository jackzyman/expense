package csku.expense;

import java.io.IOException;
import java.util.ArrayList;

public class Account {

    private double balance;
    private ArrayList<AccountHistory> histories = new ArrayList<>();



    public ArrayList<AccountHistory> getHistories() {
        return histories;
    }

    public Account(double initialMoney){
        this.balance = initialMoney;

    }

    public Account(){
        this.balance = 0;
    }


    public void income(double money, String desc, String date)  {
            balance += money;
            histories.add(new AccountHistory(date, desc, money, "+"));
//            logs.logging(getTotal());
    }

    public void expense(double money, String desc, String date) throws OverExpenseException{
            balance -= money;
        histories.add(new AccountHistory(date, desc, money, "-"));
            if(money > balance) {
                throw new OverExpenseException("be careful! your expense more than your balance\n");
            }
    }

    @Override
    public String toString() {
        return
                "Balance : " + balance
                ;
    }

    public String getTotal(){
        String temp = "\n";
        double income = 0;
        double expense = 0;
        for(int i = 0; i < histories.size(); i++){
            temp += histories.get(i).type + "" + histories.get(i).money + " : " + histories.get(i).des + "\n";
            if(histories.get(i).type == "+"){
                income += histories.get(i).money;
            }else{
                expense += histories.get(i).money;
            }
        }
        temp += "\n>>>>> Income : " + income + "\n>>>>> Expense : " + expense;

        return temp;
    }

    public double getBalance() {
        return balance;
    }

    private void sumNewBalance(){
        this.balance = 0;
        for(AccountHistory obj : this.histories){
            if (obj.getType().equals("+")) {
                this.balance += obj.money;
            } else {
                this.balance -= obj.money;
            }
        }
    }

    public void editHistory(int index ,AccountHistory newhis){
        this.histories.set(index, newhis);
        this.sumNewBalance();
    }


}
