package csku.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class DbFileConnector implements Conectable {
    private static final String fileName = "test.txt";
    private static String line = null;

    private static final DbFileConnector instance = new DbFileConnector();

    public static DbFileConnector getInstance() {
        return instance;
    }

    @Override
    public ObservableList<AccountHistory> viewHistory() {
        ObservableList<AccountHistory> informations = FXCollections.observableArrayList();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] tmp = line.split(" ");
                informations.add(new AccountHistory(tmp[0], tmp[1] + " " + tmp[2], Double.parseDouble(tmp[3]), tmp[4]));
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return informations;
    }

    @Override
    public void add(String date, String desc, double amount, String type) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(date + " " + desc + " " + amount + " " + type);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    static public void edit(String lists) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(lists);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
