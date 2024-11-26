package org.cms.canteenmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EmployeePage implements Initializable {
    @FXML
    private TableView<ProductSearch> OrderTableView;
    @FXML
    private TableColumn<ProductSearch,Integer> OrderIDCol;
    @FXML
    private TableColumn<ProductSearch,String> OrderNameCol;
    @FXML
    private TableColumn<ProductSearch,String> OrderQuantityCol;
    @FXML
    private TableColumn<ProductSearch,String> OrderPriceCol;
    @FXML
    private Button CloseBtn;
    @FXML
    private TextField IDTF;
    ObservableList<ProductSearch> productSearchObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "select id,name,quantity,price from orderlist";

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

            OrderIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            OrderNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            OrderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            OrderPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

            OrderTableView.setItems(productSearchObservableList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OrderTableView.setRowFactory( tv -> {
            TableRow<ProductSearch> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    int myIndex = OrderTableView.getSelectionModel().getSelectedIndex();
                    IDTF.setText(String.valueOf(OrderTableView.getItems().get(myIndex).getID()));
                }
            });
            return myRow;
        });
    }
    PreparedStatement pst;
    public void DeleteOrder(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String id = IDTF.getText();

        try {
            pst = connectDB.prepareStatement("delete from orderlist where ID = ? ");
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Order Confirmation Message");
            alert.setHeaderText("DELETE ORDER");
            alert.setContentText("Order's Information Deleted Successfully!");
            alert.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    public void cancelButtonOnAction(){
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void BackBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeeLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton = (Node) event.getSource();
        Stage myStage = (Stage) oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }
}