package org.cms.canteenmanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseUser="root";
        String databasePassword = "";
        String databaseName = "cafeteria_database";
        String url = "jdbc:mysql://localhost:3306/cafeteria_database";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return  databaseLink;
    }
}
