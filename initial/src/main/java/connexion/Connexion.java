/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

/**
 *
 * @author nyamp
 */
public class Connexion {
    public Connection getConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vehicule","projet","1234");
        }
        catch(ClassNotFoundException | SQLException e){ 
            System.out.println(e);
            throw e;
        }
        return con;
    }
    
    public Date getCurrent_date(Connection connection) throws Exception {
        Date date = null;
        Statement stmt = connection.createStatement();
        String sql = "select current_date";
        ResultSet res = stmt.executeQuery(sql);
        while(res.next()) {
            date = res.getDate(1);
        }
        return date;
    }
    
    public Time getCurrent_time(Connection connection) throws Exception {
        Time time = null;
        Statement stmt = connection.createStatement();
        String sql = "select * from current_time";
        ResultSet res = stmt.executeQuery(sql);
        while(res.next()) {
            time = res.getTime(1);
        }
        return time;
    }
}
