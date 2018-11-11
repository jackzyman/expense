package csku.expense;

import java.util.UUID;

public class AccountHistory {

    double money;
    String des;
    String type;
    String date;

    public AccountHistory(String date,String des, double money, String type) {
        this.money = money;
        this.des = des;
        this.type = type;
        this.date = date;

    }

    public double getMoney() {
        return money;
    }

    public String getDes() {
        return des;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

}
