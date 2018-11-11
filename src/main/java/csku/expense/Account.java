package csku.expense;

import java.util.ArrayList;

public class Account {

    private double balance;

    private ArrayList<AccountHistory> histories = new ArrayList<>();


    public Account(double initialMoney){
        this.balance = initialMoney;

    }

    public Account(){
        this.balance = 0;
    }


    public void income(double money, String desc){
            balance += money;
            histories.add(new AccountHistory(money, desc, "+"));
    }

    public void expense(double money, String desc) throws OverExpenseException{
            balance -= money;
        histories.add(new AccountHistory(money, desc, "-"));
            if(money > balance) {
                throw new OverExpenseException("be careful! your expense more than your balance\n");
            }
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

}
