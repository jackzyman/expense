package csku.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbConnector implements Conectable {
    private static String myDriver = "org.sqlite.JDBC";
    private static String urlDB = "jdbc:sqlite:database.db";

    private static final DbConnector instance = new DbConnector();

    public static DbConnector getInstance() {
        return instance;
    }

    private DbConnector(){}

    @Override
    public ObservableList<AccountHistory> viewHistory() {
        ObservableList<AccountHistory> informations = FXCollections.observableArrayList();
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "select * from StorageInfo";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String date = resultSet.getString("Dates");
                    String desc = resultSet.getString("Description");
                    double money = resultSet.getDouble("Money");
                    String type = resultSet.getString("Type");
                    informations.add(new AccountHistory(date, desc, money, type));
                }
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return informations;
    }

    @Override
    public void add(String date, String desc, double amount, String type) {
        try {
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(urlDB);
            if (connection != null) {
                String query = "insert into StorageInfo (Dates, Description, Money, Type) values ('" + date + "' , '" + desc + "' , '" + amount + "', '" + type + "')";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
            }
            connection.close();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

