/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outil;

import connexion.Connexion;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

/**
 *
 * @author nyamp
 */
public class CurrentDateheure {
    public static Date getCurrent_date() throws Exception {
        Date date = null;
        Statement stmt = new Connexion().getConnection().createStatement();
        String sql = "select current_date";
        ResultSet res = stmt.executeQuery(sql);
        while(res.next()) {
            date = res.getDate(1);
        }
        return date;
    }
    
    public static Time getCurrent_time() throws Exception {
        Time time = null;
        Statement stmt = new Connexion().getConnection().createStatement();
        String sql = "select * from current_time";
        ResultSet res = stmt.executeQuery(sql);
        while(res.next()) {
            time = res.getTime(1);
        }
        return time;
    }
}
