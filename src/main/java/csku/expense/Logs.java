package csku.expense;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logs {
    static FileHandler fileTxt;
    static SimpleFormatter formatterTxt;


    static public void logging(String logs) throws IOException {

        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);//Loget Info, Warning dhe Severe do ruhen
        fileTxt = new FileHandler("/Users/mp/Desktop/expense/logs.txt", true);
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
        logger.info(logs);


    }
}

