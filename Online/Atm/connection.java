package Online.Atm;

import java.sql.*;

public class connection {
    public Statement statement;
    private Connection con;

    public connection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebank", "root", "sam1234");
            statement = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter method for the Connection object
    public Connection getConnection() {
        return con;
    }
}
