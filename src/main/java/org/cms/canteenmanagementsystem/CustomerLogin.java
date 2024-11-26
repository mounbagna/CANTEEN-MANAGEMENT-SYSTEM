package org.cms.canteenmanagementsystem;

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

import static java.lang.Double.parseDouble;

public class CustomerLogin  implements Initializable{
    @FXML
    private TableView<ProductSearch> OrderTableView;
    @FXML
    private TableView<ProductSearch> MenuTableView;
    @FXML
    private TableColumn<ProductSearch,Integer> MenuIDCol;
    @FXML
    private TableColumn<ProductSearch,String> MenuNameCol;
    @FXML
    private TableColumn<ProductSearch,String> MenuQuantityCol;
    @FXML
    private TableColumn<ProductSearch,String> MenuPriceCol;
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
    private TextField SearchTF;
    @FXML
    private TextField NameTF;
    @FXML
    private TextField IDTF;
    @FXML
    private TextField PriceTF;
    @FXML
    private TextField QuantityTF;
    @FXML
    private ImageView myImageView;
    @FXML
    private TextField MenuAmountTF;
    @FXML
    private Label MenuChangeLabel;
    @FXML
    private Label MenuTotalLabel;
    ObservableList<ProductSearch> productSearchObservableList = FXCollections.observableArrayList();
    public void cancelButtonOnAction(){
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void BackBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerLoginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Node oldButton =(Node) event.getSource () ;
        Stage myStage = (Stage)oldButton.getScene().getWindow();
        myStage.setScene(scene);
        myStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File myFile = new File("images/foodIcon.jpg");
        Image myImage = new Image(myFile.toURI().toString());
        myImageView.setImage(myImage);

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

            OrderIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            OrderNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            OrderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            OrderPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

            MenuIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            MenuNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            MenuQuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            MenuPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

            OrderTableView.setItems(productSearchObservableList);

            //Search Key
            FilteredList<ProductSearch> filteredData = new FilteredList<>(productSearchObservableList,b ->true);
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

            sortedData.comparatorProperty().bind(OrderTableView.comparatorProperty());
            OrderTableView.setItems(sortedData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Initialising textFields
        OrderTableView.setRowFactory( tv -> {
            TableRow<ProductSearch> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    int myIndex = OrderTableView.getSelectionModel().getSelectedIndex();
                    IDTF.setText(String.valueOf(OrderTableView.getItems().get(myIndex).getID()));
                    NameTF.setText(OrderTableView.getItems().get(myIndex).getName());
                    PriceTF.setText(String.valueOf(OrderTableView.getItems().get(myIndex).getPrice()));
                    QuantityTF.setText(String.valueOf(OrderTableView.getItems().get(myIndex).getQuantity()));
                }
            });
            return myRow;
        });
    }


@FXML
private void Add(ActionEvent event) throws SQLException {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    String id = IDTF.getText();
    String name = NameTF.getText();
    String quantity = QuantityTF.getText();
    String price = PriceTF.getText();

    String insertValues = id + "','" + name + "','" + quantity + "','" + price + "' )";
    String insertFields = "INSERT into orderlist(ID,Name,Quantity,Price) VALUES ('";
    String insertOrder = insertFields + insertValues;

    try {
    Statement statement = connectDB.createStatement();
    statement.executeUpdate(insertOrder);

    ProductSearch product = new ProductSearch(Integer.parseInt(IDTF.getText()),NameTF.getText(),Integer.parseInt(QuantityTF.getText()), parseDouble(PriceTF.getText()));
    ObservableList<ProductSearch> products = MenuTableView.getItems();

    products.add(product);
    MenuTableView.setItems(products);

    Double sum;

    sum = parseDouble(PriceTF.getText()) * Integer.parseInt(QuantityTF.getText()) + parseDouble(MenuTotalLabel.getText());

    MenuTotalLabel.setText(Double.toString(sum));
    }
    catch (Exception e){
        e.printStackTrace();
        e.getCause();
    }
}
@FXML
private void MenuRemoveBtn(ActionEvent event) {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    String id = IDTF.getText();

    try {
        PreparedStatement pst;
        pst = connectDB.prepareStatement("delete from orderlist where ID = ? ");
        pst.setInt(1, Integer.parseInt(id));
        pst.executeUpdate();

        ObservableList<ProductSearch> allProducts, singleProduct;
        allProducts = MenuTableView.getItems();
        singleProduct = MenuTableView.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);

        Double difference;
        difference = parseDouble(MenuTotalLabel.getText()) - parseDouble(PriceTF.getText()) * Integer.parseInt(QuantityTF.getText());

        MenuTotalLabel.setText(Double.toString(difference));
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    @FXML
    private void ReceiptBtn(ActionEvent event){
        Double amount= parseDouble(MenuAmountTF.getText());
        Double total= parseDouble(MenuTotalLabel.getText());
        Double difference=amount-total;
        MenuChangeLabel.setText(Double.toString(difference));
    }
    @FXML
    private void ResetOrder(ActionEvent event){
        MenuTotalLabel.setText(Double.toString(0.0));
        MenuAmountTF.setText(Double.toString(0.0));
        MenuChangeLabel.setText(Double.toString(0.0));
    }
}
