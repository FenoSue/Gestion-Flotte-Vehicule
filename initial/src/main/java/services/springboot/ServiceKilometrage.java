/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.HttpRetour;
import classes.springboot.Kilometrage;
import classes.springboot.Token;
import java.sql.Date;

/**
 *
 * @author USER
 */
public class ServiceKilometrage {
    Kilometrage k = new Kilometrage();
    HttpRetour h = new HttpRetour();
    
    public HttpRetour insertKilometrage(String token, int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        if(token!=null) {
            boolean resultat = new Token().verifieToken(token);
            if(resultat==true) {
                if(idVehicule!=0 || dateKilometrage!=null || debutKilometrage!=0 || finKilometrage!=0) {
                    k.create(idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
                    Kilometrage kilometrage = new Kilometrage(idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
                    Object[] data = new Object[1];
                    data[0] = kilometrage;
                    h.setHttpRetour(h, 200, "Ok", data);
                }
                else {
                    String[] data = new String[4];
                    int i=0;
                    for(i=0; i<data.length; i++) {
                        data[i] = "Champ obligatoire";
                    }
                    h.setHttpRetour(h, 505, "Erreur", data);
                }
            }
            else {
                String[] data = new String[1];
                data[0] = "Vous devez vous deconnectez";
                h.setHttpRetour(h, 506, "Erreur", null);
            }
        }
        else {
            h.setHttpRetour(h, 507, "Erreur", null);
        }
        return h;
    }
    
    public HttpRetour getKilometrage(int idKilometrage) throws Exception {
        Kilometrage kilometrage = k.getById(idKilometrage);
        if(kilometrage.getId()!=0) {
            Object[] data = new Object[1];
            data[0] = kilometrage;
            h.setHttpRetour(h, 200, "Ok", data);
        }
        else {
            String[] data = new String[1];
            data[0] = "Aucun objet trouvé correspondant à idKilometrage = "+idKilometrage;
            h.setHttpRetour(h, 505, "Erreur", data);
        }
        return h;
    }
    
    public HttpRetour listeKilometrage() throws Exception {
        Kilometrage[] kilometrage = k.read();
        if(kilometrage.length!=0) {
            h.setHttpRetour(h, 200, "Ok", kilometrage);
        }
        else {
            String[] data = new String[1];
            data[0] = "Null";
            h.setHttpRetour(h, 500, "Erreur", data);
        }
        return h;
    }
    
    public HttpRetour getKilometrageVehicule(int idVehicule) throws Exception {
        Kilometrage kilometrage = new Kilometrage();
        Kilometrage[] kilometrages = k.read();
        int i=0;
        for(i=0; i<kilometrages.length; i++) {
            if(kilometrages[i].getIdVehicule()==idVehicule) {
                kilometrage = kilometrages[i];
            }
        }
        if(kilometrage!=null) {
            Object[] data = new Object[1];
            data[0] = kilometrage;
            h.setHttpRetour(h, 200, "Ok", data);
        }
        return h;
    }
    
    public HttpRetour updateKilometrage(int idKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        HttpRetour kilometrage = this.getKilometrage(idKilometrage);
        if(kilometrage.getStatus()==200) {
            Object[] data = kilometrage.getData();
            Kilometrage kilometre = (Kilometrage) data[0];
            Kilometrage newKilometrage = new Kilometrage();
            newKilometrage.setDebutKilometrage(debutKilometrage);
            newKilometrage.setFinKilometrage(finKilometrage);
            k.update(kilometre, newKilometrage);
            String[] newData = new String[1];
            data[0] = "Succès";
            h.setHttpRetour(h, 200, "Ok", newData);
        }
        else {
            String[] data = new String[1];
            data[0] = "Failed";
            h.setHttpRetour(h, 500, "Erreur", data);
        }
        return h;
    }
    
    public HttpRetour deleteKilometrage(int idKilometrage) throws Exception {
        if(idKilometrage!=0) {
            Kilometrage kilometrage = k.getById(idKilometrage);
            k.delete(kilometrage);
            String[] data = new String[1];
            data[0] = "Succès";
            h.setHttpRetour(h, 200, "Ok", data);
        }
        else {
            String[] data = new String[1];
            data[0] = "Aucune kilometrage correspondant à idKilometrage = "+idKilometrage;
            h.setHttpRetour(h, 500, "Erreur", data);
        }
        return h;
    }
}
