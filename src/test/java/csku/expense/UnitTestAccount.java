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
        account.income(100, "earned from work");
        assertEquals(200, account.getBalance());
    }

    @Test
    void testExpense() throws OverExpenseException {
        account.expense(50, "buy egg");
        assertEquals(50, account.getBalance());
    }

    @Test
    @DisplayName("throws OverExpenseException when expense more than balance")
    void testExpenseMoreThanBalance(){
        assertThrows(OverExpenseException.class,
                () -> account.expense(200, "buy steak"));
        assertEquals(-100, account.getBalance());
    }


}