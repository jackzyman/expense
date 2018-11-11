package csku.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTestAccount {

    Account account;
    int init = 100;

    @BeforeEach
    void init(){
        account = new Account(init);

    }

    @Test
    void testIncome(){
        account.income(100, "earned from work", "16-09-2018");
        assertEquals(200, account.getBalance());
    }

    @Test
    void testExpense() throws OverExpenseException {
        account.expense(50, "buy egg", "16-09-2018");
        assertEquals(50, account.getBalance());
    }

    @Test
    void testEditHistory(){
        account.income(100, "earned from work", "16-09-2018");
        account.income(200, "earned from mom", "16-09-2018");

        AccountHistory beforeEdit = account.getHistories().get(0);

        AccountHistory edited = new AccountHistory(beforeEdit.date, beforeEdit.des, 200, beforeEdit.date);
        account.editHistory(0, edited);

        assertEquals(400, account.getBalance());
    }

    @Test
    @DisplayName("throws OverExpenseException when expense more than balance")
    void testExpenseMoreThanBalance(){
        assertThrows(OverExpenseException.class,
                () -> account.expense(200, "buy steak", "16-09-2018"));
        assertEquals(-100, account.getBalance());
    }


}