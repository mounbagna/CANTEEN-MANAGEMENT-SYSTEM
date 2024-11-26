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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FoodMenu implements Initializable {
    @FXML
    private TableView<ProductSearch> MenuTableView;
    @FXML
    private TableColumn<ProductSearch,Integer> IDCol;
    @FXML
    private TableColumn<ProductSearch,String> NameCol;
    @FXML
    private TableColumn<ProductSearch,String> QuantityCol;
    @FXML
    private TableColumn<ProductSearch,String> PriceCol;
    @FXML
    private TextField SearchTF;

    @FXML
    private ImageView FoodImageView;
    @FXML
    private TextField IDTF;
    @FXML
    private TextField NameTF;
    @FXML
    private TextField PriceTF;
    @FXML
    private TextField QuantityTF;
    @FXML
    private Button CloseBtn;
    ObservableList<ProductSearch> productSearchObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        File welcomeImageFile = new File("images/foodIcon.jpg");
        Image welcomeImage = new Image(welcomeImageFile.toURI().toString());
        FoodImageView.setImage(welcomeImage);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select id,name,quantity,price from food";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet QueryOutput = statement.executeQuery(query);
            while(QueryOutput.next()){

                Integer FID = QueryOutput.getInt("ID");
                String FName = QueryOutput.getString("Name");
                Integer FQuantity = QueryOutput.getInt("Quantity");
                Double FPrice = QueryOutput.getDouble("Price");

                //Populate the ObservableList
                productSearchObservableList.add(new ProductSearch(FID,FName,FQuantity,FPrice));
            }

            IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            PriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

            MenuTableView.setItems(productSearchObservableList);

            FilteredList<ProductSearch> filteredData = new FilteredList<>(productSearchObservableList, b ->true);
            SearchTF.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(productSearch -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String SearchKeyword = newValue.toLowerCase();
                    if(productSearch.getName().toLowerCase().indexOf(SearchKeyword) > -1){
                        return true;
                    }
                    else{
                        return false;
                    }
                });
            });

            SortedList<ProductSearch> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(MenuTableView.comparatorProperty());
            MenuTableView.setItems(sortedData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MenuTableView.setRowFactory( tv -> {
            TableRow<ProductSearch> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    int myIndex = MenuTableView.getSelectionModel().getSelectedIndex();
                    IDTF.setText(String.valueOf(MenuTableView.getItems().get(myIndex).getID()));
                    NameTF.setText(MenuTableView.getItems().get(myIndex).getName());
                    PriceTF.setText(String.valueOf(MenuTableView.getItems().get(myIndex).getPrice()));
                    QuantityTF.setText(String.valueOf(MenuTableView.getItems().get(myIndex).getQuantity()));
                }
            });
            return myRow;
        });
    }
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


    public void AddFood(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String id = IDTF.getText();
        String name = NameTF.getText();
        String quantity = QuantityTF.getText();
        String price = PriceTF.getText();

        String insertValues = id + "','" + name + "','" + quantity + "','" + price + "' )";
        String insertFields = "INSERT into Food(ID,Name,Quantity,Price) VALUES ('";
        String insertItems = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertItems);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Food Confirmation Message");
            alert.setHeaderText("Add Food");
            alert.setContentText("Food Information Added Successfully!");
            alert.showAndWait();

            IDTF.setText("");
            NameTF.setText("");
            QuantityTF.setText("");
            PriceTF.setText("");
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    PreparedStatement pst;
    public void DeleteFood(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String id = IDTF.getText();

        String query = "delete from Food where ID = ? ";

        try {
            pst = connectDB.prepareStatement("delete from Food where ID = ? ");
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Food Confirmation Message");
            alert.setHeaderText("DELETE FOOD");
            alert.setContentText("Food's Information Deleted Successfully!");
            alert.showAndWait();

            IDTF.setText("");
            NameTF.setText("");
            QuantityTF.setText("");
            PriceTF.setText("");
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

        String name,quantity,id,price;

        int myIndex = MenuTableView.getSelectionModel().getSelectedIndex();
        id = String.valueOf(Integer.parseInt(String.valueOf(MenuTableView.getItems().get(myIndex).getID())));
        name = NameTF.getText();
        quantity = QuantityTF.getText();
        price = PriceTF.getText();

        try
        {
            pst = connectDB.prepareStatement("update Food set name = ?, Quantity = ?, Price = ?where ID = ? ");
            pst.setString(1, name);
            pst.setInt(2, Integer.parseInt(quantity));
            pst.setDouble(3, Double.parseDouble(price));
            pst.setInt(4, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Food Confirmation Message");
            alert.setHeaderText("Food Update");
            alert.setContentText("Food Information Updated Successfully!");
            alert.showAndWait();

            IDTF.setText("");
            NameTF.setText("");
            PriceTF.setText("");
            QuantityTF.setText("");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
