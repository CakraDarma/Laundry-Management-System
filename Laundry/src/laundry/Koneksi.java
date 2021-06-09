
package laundry;
import java.sql.*;
import javax.swing.*;

public class Koneksi {
    private static Connection connection;
    public static Connection getConnection(){
        JFrame frame = new JFrame();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_laundry","root","");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame,"Koneksi Error!","Gagal Masuk Database",JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }
}
