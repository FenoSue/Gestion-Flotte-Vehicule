/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.Admin;

/**
 *
 * @author USER
 */
public class ServiceAdmin {
    Admin a = new Admin();
    
    public Admin[] listeAdmin() throws Exception {
        Admin[] admin = a.read();
        return admin;
    }
    
    public boolean Connection(String login, String pwd) throws Exception {
        boolean resultat = false;
        Admin[] listeAdmin = this.listeAdmin();
        int i=0;
        for(i=0; i<listeAdmin.length; i++) {
            if(listeAdmin[i].getLogin().equals(login)==true && listeAdmin[i].getPwd().equals(pwd)==true) {
                resultat = true;
            }
        }
        return resultat;
    }
}
