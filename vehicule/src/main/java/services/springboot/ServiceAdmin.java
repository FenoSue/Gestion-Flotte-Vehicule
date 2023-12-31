/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.Admin;
import classes.springboot.HttpRetour;
import classes.springboot.Token;

/**
 *
 * @author USER
 */
public class ServiceAdmin {
    private Token token = new Token();
    Admin a = new Admin();
    HttpRetour h = new HttpRetour();
    
    public Admin[] listeAdmin() throws Exception {
        Admin[] admin = a.read();
        return admin;
    }
    
    public HttpRetour connection(String login, String pwd) throws Exception {
        boolean resultat = false;
        Admin admin = null;
        Admin[] listeAdmin = this.listeAdmin();
        int i=0;
        for(i=0; i<listeAdmin.length; i++) {
            if(listeAdmin[i].getLogin().equals(login)==true && listeAdmin[i].getPwd().equals(pwd)==true) {
                resultat = true;
                admin = listeAdmin[i];
            }   
        }
        if(resultat==true) {
            String[] data = new String[1];
            String genererToken = token.genererToken(admin.getId());
            data[0] = genererToken;
            h.setHttpRetour(h, 200, "Ok", data);
        }
        else if(resultat==false) {
            String[] data = new String[2];
            data[0] = "Login obligatoire";
            data[1] = "Passeword obligatoire";
            h.setHttpRetour(h, 400, "Erreur", data);
        }
        return h;
    }
    
    public HttpRetour deconnection(String tokenUtilisateur) throws Exception {
        Token tokenAdmin = token.readByToken(tokenUtilisateur);
        String data[] = new String[1];
        if(tokenAdmin.getToken() == null==false) { 
            token.delete(tokenAdmin);
            data[0] = "Deconnecté";
            h.setHttpRetour(h, 200, "Ok", data);
        }
        else {
            data[0] = "Erreur";
            h.setHttpRetour(h, 400, "Erreur", data);
        }
        return h;
    }
}
