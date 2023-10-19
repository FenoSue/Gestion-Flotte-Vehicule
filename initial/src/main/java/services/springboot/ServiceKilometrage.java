/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.Kilometrage;
import java.sql.Date;

/**
 *
 * @author USER
 */
public class ServiceKilometrage {
    Kilometrage k = new Kilometrage();
    
    public void insertKilometrage(int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        k.create(idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
    }
    
    public Kilometrage getKilometrage(int idKilometrage) throws Exception {
        Kilometrage kilometrage = k.getById(idKilometrage);
        return kilometrage;
    }
    
    public Kilometrage[] listeKilometrage() throws Exception {
        Kilometrage[] kilometrage = k.read();
        return kilometrage;
    }
    
    public Kilometrage getKilometrageVehicule(int idVehicule) throws Exception {
        Kilometrage kilometrage = new Kilometrage();
        Kilometrage[] kilometrages = k.read();
        int i=0;
        for(i=0; i<kilometrages.length; i++) {
            if(kilometrages[i].getIdVehicule()==idVehicule) {
                kilometrage = kilometrages[i];
            }
        }
        return kilometrage;
    }
    
    public void updateKilometrage(int idKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        Kilometrage kilometrage = this.getKilometrage(idKilometrage);
        Kilometrage newKilometrage = new Kilometrage();
        newKilometrage.setDebutKilometrage(debutKilometrage);
        newKilometrage.setFinKilometrage(finKilometrage);
        k.update(kilometrage, newKilometrage);
    }
    
    public void deleteKilometrage(int idKilometrage) throws Exception {
        Kilometrage kilometrage = k.getById(idKilometrage);
        k.delete(kilometrage);
    }
}
