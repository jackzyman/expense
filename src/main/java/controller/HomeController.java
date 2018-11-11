package controller;

import csku.expense.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;


public class HomeController {

    private Conectable connector;

    @FXML
    TableView<AccountHistory> table = new TableView<>();
    @FXML private TableColumn<AccountHistory, String> dateColumn;
    @FXML private TableColumn<AccountHistory, String> desColumn;
    @FXML private TableColumn<AccountHistory, String> amountColumn;
    @FXML private TableColumn<AccountHistory, String> typeColumn;

    @FXML private TextField dateTextField ;
    @FXML private TextField desTextField ;
    @FXML private TextField amountField;
//    @FXML private Button incomeButton;
    @FXML private Button editButton;
//    @FXML private Button expenseButton;

    @FXML private Label balanceLabel;

    Account accounts = new Account();
    private String amountBeforeEdit;
    private boolean isEdit = false;

    AccountHistory selectItem = null;
    int selectedIndex = -1;


    @FXML
    public void initialize(){
        initTableV();
    }

    void updateBalance(){
        balanceLabel.setText(accounts.toString());
    }

    void clearTextField(){
        dateTextField.setText("");
        desTextField.setText("");
        amountField.setText("");
    }

    private void initTableV(){
        connector = DbConnector.getInstance();

        ObservableList<AccountHistory> histories = FXCollections.observableArrayList();
        for(AccountHistory obj : accounts.getHistories()){
            histories.add(new AccountHistory(obj.getDate(), obj.getDes(), obj.getMoney(), obj.getType()));
        }

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("des"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        table.setItems(connector.viewHistory());
        updateBalance();
//        clearTextField();
    }

    @FXML
    public void handleIncomBtn(ActionEvent event){
        if(!dateTextField.getText().isEmpty() && !desTextField.getText().isEmpty() && !amountField.getText().isEmpty()){





            if(isEdit){
                accounts.editHistory(selectedIndex, new AccountHistory(dateTextField.getText(), desTextField.getText(), Double.parseDouble(amountField.getText()), "+"));
                isEdit = false;
                editButton.setDisable(false);
            } else {
                accounts.income(Double.parseDouble(amountField.getText()), desTextField.getText(), dateTextField.getText());
                connector.add(dateTextField.getText(), desTextField.getText(), Double.parseDouble(amountField.getText()), "+");
            }

            updateBalance();
            clearTextField();
            initTableV();
        }
    }



    @FXML
    public void handleExpenseBtn(ActionEvent event){

            if(!dateTextField.getText().isEmpty() && !desTextField.getText().isEmpty() && !amountField.getText().isEmpty()){
                try {
                    if(isEdit){
                        accounts.editHistory(selectedIndex, new AccountHistory(dateTextField.getText(), desTextField.getText(), Double.parseDouble(amountField.getText()), "--"));
                        isEdit = false;
                        editButton.setDisable(false);
                    } else {
                        accounts.expense(Double.parseDouble(amountField.getText()), desTextField.getText(), dateTextField.getText());
                        connector.add(dateTextField.getText(), desTextField.getText(), Double.parseDouble(amountField.getText()), "-");
                    }

                } catch (OverExpenseException e) {
                    e.getMessage();
                }

                updateBalance();
                clearTextField();
                initTableV();
            }
    }

    @FXML
    public void handleEditBtn(ActionEvent event){
        if(!table.getSelectionModel().isEmpty()){

            this.selectedIndex = table.getSelectionModel().getSelectedIndex();

            amountBeforeEdit = String.valueOf(table.getSelectionModel().getSelectedItem().getMoney());
            desTextField.setText(table.getSelectionModel().getSelectedItem().getDes());
            dateTextField.setText(table.getSelectionModel().getSelectedItem().getDate());
            amountField.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getMoney()));


            editButton.setDisable(true);
            isEdit = true;
            //initTableV();
        }

    }

    public void switchDB() {
        if (DbConnector.class == connector.getClass()) {
            connector = DbFileConnector.getInstance();
        }
        else {
            connector = DbConnector.getInstance();
        }
        table.setItems(connector.viewHistory());
    }




}
