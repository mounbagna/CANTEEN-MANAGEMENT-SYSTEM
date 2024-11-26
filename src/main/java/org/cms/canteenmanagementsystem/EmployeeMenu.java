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

public class EmployeeMenu implements Initializable {
    @FXML
    private TableView<EmployeeSearch> EmployeeTableView;
    @FXML
    private TableColumn<EmployeeSearch,Integer> IDCol;
    @FXML
    private TableColumn<EmployeeSearch,String> NameCol;
    @FXML
    private TableColumn<EmployeeSearch,String> PasswordCol;
    @FXML
    private TableColumn<EmployeeSearch,String> AdressCol;
    @FXML
    private TableColumn<EmployeeSearch,String> EmailCol;
    @FXML
    private TableColumn<EmployeeSearch,Integer> TelephoneCol;
    @FXML
    private TextField SearchTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField TelephoneTF;
    @FXML
    private TextField AdressTF;
    @FXML
    private ImageView EmployeeImageView;
    @FXML
    private TextField IDTF;
    @FXML
    private TextField NameTF;
    @FXML
    private PasswordField PasswordTF;
    @FXML
    private Button CloseBtn;
    ObservableList<EmployeeSearch> employeeSearchObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        File welcomeImageFile = new File("images/home-background-image.png");
        Image welcomeImage = new Image(welcomeImageFile.toURI().toString());
        EmployeeImageView.setImage(welcomeImage);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select E_ID,Name,Password,Email,Telephone,Adress from employeeaccount";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet QueryOutput = statement.executeQuery(query);
            while(QueryOutput.next()){

                Integer EID = QueryOutput.getInt("E_ID");
                String EName = QueryOutput.getString("Name");
                String EPassword = QueryOutput.getString("Password");
                String EEmail = QueryOutput.getString("Email");
                Integer ETelephone = QueryOutput.getInt("Telephone");
                String EAdress = QueryOutput.getString("Adress");

                //Populate the ObservableList
                employeeSearchObservableList.add(new EmployeeSearch(EID,EName,EPassword,EEmail,EAdress,ETelephone));
            }

            IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            PasswordCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
            EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
            TelephoneCol.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
            AdressCol.setCellValueFactory(new PropertyValueFactory<>("Adress"));

            EmployeeTableView.setItems(employeeSearchObservableList);

            FilteredList<EmployeeSearch> filteredData = new FilteredList<>(employeeSearchObservableList, b ->true);
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

            SortedList<EmployeeSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(EmployeeTableView.comparatorProperty());
            EmployeeTableView.setItems(sortedData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        EmployeeTableView.setRowFactory( tv -> {
            TableRow<EmployeeSearch> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    int myIndex = EmployeeTableView.getSelectionModel().getSelectedIndex();
                    IDTF.setText(String.valueOf(EmployeeTableView.getItems().get(myIndex).getID()));
                    NameTF.setText(EmployeeTableView.getItems().get(myIndex).getName());
                    PasswordTF.setText(EmployeeTableView.getItems().get(myIndex).getPassword());
                    EmailTF.setText(EmployeeTableView.getItems().get(myIndex).getEmail());
                    AdressTF.setText(EmployeeTableView.getItems().get(myIndex).getAdress());
                    TelephoneTF.setText(String.valueOf(EmployeeTableView.getItems().get(myIndex).getID()));
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

        String query = "delete from employeeaccount where E_ID = ? ";

        try {
            pst = connectDB.prepareStatement("delete from employeeaccount where E_ID = ? ");
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Employee Confirmation Message");
            alert.setHeaderText("DELETE EMPLOYEE");
            alert.setContentText("Employee Information Deleted Successfully!");
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

        int myIndex = EmployeeTableView.getSelectionModel().getSelectedIndex();
        id = String.valueOf(Integer.parseInt(String.valueOf(EmployeeTableView.getItems().get(myIndex).getID())));
        name = NameTF.getText();
        password = PasswordTF.getText();
        Email = EmailTF.getText();
        Adress = AdressTF.getText();
        Telephone = TelephoneTF.getText();

        try
        {
            pst = connectDB.prepareStatement("update employeeaccount set Name = ?,Password = ?,Email = ?,Telephone = ?,Adress = ? where E_ID = ? ");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, Email);
            pst.setInt(4, Integer.parseInt(Telephone));
            pst.setString(5, Adress);
            pst.setInt(6, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Employee Confirmation Message");
            alert.setHeaderText("Employee Update");
            alert.setContentText("Employee Information Updated Successfully!");
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





