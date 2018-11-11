package csku.expense;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TempMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

       AccountHistory accountInformation = context.getBean("accountInformation", AccountHistory.class);
    }
}
