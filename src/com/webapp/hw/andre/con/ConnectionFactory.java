/*************************************
*
* Student Name: Andre Barbosa
* Assingment: #4
* Class: CIS 4160
*
*
*
************************************/

package com.webapp.hw.andre.con;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Class that is responsible for doing the connection with the Database
public class ConnectionFactory {

    /*private static Connection connection;
        static {
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.3:1521:XE", "cis4160", "040294");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    public Connection getConnection() {
        return connection;
    }*/

    public Connection getConnection() throws SQLException {
        System.out.println("connecting ...");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }

        return DriverManager.getConnection("jdbc:oracle:thin:@10.211.55.3:1521:XE", "cis4160", "040294");
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        System.out.println("Connection Openned!");
        connection.close();
    }
}
