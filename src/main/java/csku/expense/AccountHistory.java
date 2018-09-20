package csku.expense;

import java.util.UUID;

public class AccountHistory {

    double money;
    String des;
    String type;
    String date;

    public AccountHistory(double money, String des, String type, String date) {
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
