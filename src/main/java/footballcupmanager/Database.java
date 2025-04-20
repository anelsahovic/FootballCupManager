package footballcupmanager;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDb(){
        String databaseUser = "root";
        String databasePassword = "12345678";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/footballcupmanager",databaseUser,databasePassword);
                    return connect;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
