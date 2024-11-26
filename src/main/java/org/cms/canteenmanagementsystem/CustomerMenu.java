package org.cms.canteenmanagementsystem;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerMenu implements Initializable {
    @FXML
    private TableView<CustomerSearch> CustomerTableView;
    @FXML
    private TableColumn<CustomerSearch,Integer> IDCol;
    @FXML
    private TableColumn<CustomerSearch,String> NameCol;
    @FXML
    private TableColumn<CustomerSearch,String> PasswordCol;
    @FXML
    private TableColumn<CustomerSearch,String> AdressCol;
    @FXML
    private TableColumn<CustomerSearch,String> EmailCol;
    @FXML
    private TableColumn<CustomerSearch,Integer> TelephoneCol;
    @FXML
    private TextField SearchTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField TelephoneTF;
    @FXML
    private TextField AdressTF;
    @FXML
    private ImageView CustomerImageView;
    @FXML
    private TextField IDTF;
    @FXML
    private TextField NameTF;
    @FXML
    private PasswordField PasswordTF;
    @FXML
    private Button CloseBtn;
    ObservableList<CustomerSearch> customerSearchObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        File welcomeImageFile = new File("images/home-background-image.png");
        Image welcomeImage = new Image(welcomeImageFile.toURI().toString());
        CustomerImageView.setImage(welcomeImage);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select account_ID,user_Name,password,Email,Telephone,Adress from useraccount";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet QueryOutput = statement.executeQuery(query);
            while(QueryOutput.next()){

                Integer CID = QueryOutput.getInt("account_ID");
                String CName = QueryOutput.getString("user_Name");
                String CPassword = QueryOutput.getString("password");
                String CEmail = QueryOutput.getString("Email");
                Integer CTelephone = QueryOutput.getInt("Telephone");
                String CAdress = QueryOutput.getString("Adress");

                //Populate the ObservableList
                customerSearchObservableList.add(new CustomerSearch(CID,CName,CPassword,CEmail,CAdress,CTelephone));
            }

            IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
            EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
            TelephoneCol.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
            AdressCol.setCellValueFactory(new PropertyValueFactory<>("Adress"));

            CustomerTableView.setItems(customerSearchObservableList);

            FilteredList<CustomerSearch> filteredData = new FilteredList<>(customerSearchObservableList, b ->true);
            SearchTF.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(customerSearch -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String SearchKeyword = newValue.toLowerCase();
                    if(customerSearch.getName().toLowerCase().indexOf(SearchKeyword) > -1){
                        return true;
                    }
                    else{
                        return false;
                    }
                });
            });

            SortedList<CustomerSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(CustomerTableView.comparatorProperty());
            CustomerTableView.setItems(sortedData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        CustomerTableView.setRowFactory( tv -> {
            TableRow<CustomerSearch> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    int myIndex = CustomerTableView.getSelectionModel().getSelectedIndex();
                    IDTF.setText(String.valueOf(CustomerTableView.getItems().get(myIndex).getID()));
                    NameTF.setText(CustomerTableView.getItems().get(myIndex).getName());
                    PasswordTF.setText(CustomerTableView.getItems().get(myIndex).getPassword());
                    EmailTF.setText(CustomerTableView.getItems().get(myIndex).getEmail());
                    AdressTF.setText(CustomerTableView.getItems().get(myIndex).getAdress());
                    TelephoneTF.setText(String.valueOf(CustomerTableView.getItems().get(myIndex).getID()));
                }
            });
            return myRow;
        });

    }
    @FXML
    public void CloseBtn(ActionEvent event){
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    @FXML
    public void BackBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdminMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
    PreparedStatement pst;
    public void DeleteCustomer(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String id = IDTF.getText();

        String query = "delete from useraccount where account_ID = ? ";

        try {
            pst = connectDB.prepareStatement("delete from useraccount where account_ID = ? ");
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();

            ObservableList<CustomerSearch> allProducts, singleProduct;
            allProducts = CustomerTableView.getItems();
            singleProduct = CustomerTableView.getSelectionModel().getSelectedItems();
            singleProduct.forEach(allProducts::remove);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Food Confirmation Message");
            alert.setHeaderText("DELETE CUSTOMER");
            alert.setContentText("Customer Information Deleted Successfully!");
            alert.showAndWait();

            IDTF.setText("");
            NameTF.setText("");
            PasswordTF.setText("");
            EmailTF.setText("");
            TelephoneTF.setText("");
            AdressTF.setText("");
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    void UpdateCustomer() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String name,id,password,Email,Telephone,Adress;

        int myIndex = CustomerTableView.getSelectionModel().getSelectedIndex();
        id = String.valueOf(Integer.parseInt(String.valueOf(CustomerTableView.getItems().get(myIndex).getID())));
        name = NameTF.getText();
        password = PasswordTF.getText();
        Email = EmailTF.getText();
        Adress = AdressTF.getText();
        Telephone = TelephoneTF.getText();

        try
        {
            pst = connectDB.prepareStatement("update useraccount set user_name = ?,password = ?,Email = ?,Telephone = ?,Adress = ? where account_ID = ? ");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, Email);
            pst.setInt(4, Integer.parseInt(Telephone));
            pst.setString(5, Adress);
            pst.setInt(6, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Customer Confirmation Message");
            alert.setHeaderText("Customer Update");
            alert.setContentText("Customer Information Updated Successfully!");
            alert.showAndWait();

            IDTF.setText("");
            NameTF.setText("");
            PasswordTF.setText("");
            EmailTF.setText("");
            TelephoneTF.setText("");
            AdressTF.setText("");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    }





