package footballcupmanager;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/footballcupmanager","root","password");
                    return connect;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
