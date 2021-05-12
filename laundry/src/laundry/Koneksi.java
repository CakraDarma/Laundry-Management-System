/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laundry;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Praktikan
 */
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
