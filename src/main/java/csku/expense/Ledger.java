package csku.expense;

/**
 * Created by 708 on 8/31/2018.
 */
public class Ledger {
    private int state;
    private Account account;

    public static final int TRANSACT = 2;

   public Ledger(){
       account = new Account();
       state = TRANSACT;
   }

    public Ledger(Account a){
        account = a;
        state = TRANSACT;
    }

   public void income(double money, String desc, String date){
       if(state == TRANSACT){
           account.income(money, desc, date);
       }
   }

   public void expense(double money, String desc, String date) throws OverExpenseException{
       if(state == TRANSACT) {
           account.expense(money, desc, date);
       }

   }

    public double getBalance() {
        if (state == TRANSACT) {
            return account.getBalance();
        }
        return -1;
    }

    public int getState() {
        return state;
    }

    public String getTotal(){
       return account.getTotal();
    }
}
