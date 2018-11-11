package csku.expense;

import javafx.collections.ObservableList;

public interface Conectable {
    ObservableList<AccountHistory> viewHistory();
    void add(String date, String desc, double amount, String type);
}

