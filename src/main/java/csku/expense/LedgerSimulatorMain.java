package csku.expense;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LedgerSimulatorMain {
    public static void main(String[] args) throws IOException {
        (new LedgerConsoleUI()).start();
    }
}
