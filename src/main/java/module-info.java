module org.cms.canteenmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens org.cms.canteenmanagementsystem to javafx.fxml;
    exports org.cms.canteenmanagementsystem;
}