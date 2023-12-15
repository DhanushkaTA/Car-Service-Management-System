package lk.ijse.the_car_guys.db;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static DBConnection dbConnection;

    public final Connection connection;

    private DBConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection= DriverManager.getConnection("jdbc:mysql://localhost/the_car_gays","root","1234");
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException("Failed to load the database");
        }
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            this.connection=
//                    DriverManager.
//                            getConnection(
//                                    "jdbc:mysql://localhost/the_car_gays",
//                                    "root","1234");
//        } catch (ClassNotFoundException | SQLException e ) {
//
////    public static DBConnection getInstance() {    //new Alert(Alert.AlertType.WARNING,"Failed to load the database").show();
//            throw new RuntimeException("Failed to load the database");
//        }

    }

    public static DBConnection getInstance(){
        return (dbConnection==null) ?
                dbConnection=new DBConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
