/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.HttpRetour;
import classes.springboot.Vehicule;

/**
 *
 * @author USER
 */
public class ServiceVehicule {
    HttpRetour h = new HttpRetour();
    Vehicule v = new Vehicule();
    
    public HttpRetour insertVehicule(String matricule, String marque, String modele) throws Exception {
        if(matricule!=null && marque!=null && modele!=null) {
            v.create(matricule, marque, modele);
            Object[] data = new Object[1];
            data[0] = new Vehicule(matricule, marque, modele);
            h.setHttpRetour(h, 200, "Ok", data);
        }
        else {
            String[] data = new String[1];
            data[0] = "Champ obligatoire";
            h.setHttpRetour(h, 500, "Erreur", data);
        }
        return h;
    }
    
    public HttpRetour getVehicule(int idVehicule) throws Exception {
        if(idVehicule!=0) {
            Vehicule vehicule = v.getById(idVehicule);
            Object[] data = new Object[1];
            data[0] = vehicule;
            h.setHttpRetour(h, 200, "Ok", data);
        }
        else {
            String[] data = new String[1];
            data[0] = "Aucun Vehicule correspondant à idVehicule = "+idVehicule;
            h.setHttpRetour(h, 500, "Erreur", data);
        }
        return h;
    }
    
    public HttpRetour listeVehicule() throws Exception {
        Vehicule[] vehicule = v.read();
        if(vehicule.length!=0) {
            h.setHttpRetour(h, 200, "Ok", vehicule);
        }
        else {
            h.setHttpRetour(h, 500, "Erreur", null);
        }
        return h;
    }
    
    public HttpRetour updateVehicule(int idVehicule, String matricule) throws Exception {
        HttpRetour data = this.getVehicule(idVehicule);
        if(data.getStatus()==200) {
            Vehicule vehicule = (Vehicule) data.getData()[0];
            Vehicule newVehicule = new Vehicule();
            newVehicule.setMatricule(matricule);
            v.update(vehicule, newVehicule);
        }
        else {
            h.setHttpRetour(h, 500, "Erreur", null);
        }
        return h;
    }
    
    public HttpRetour deleteVehicule(int idVehicule) throws Exception {
        HttpRetour vahicule = this.getVehicule(idVehicule);
        if(vahicule.getStatus()==200) {
            v.delete((Vehicule) vahicule.getData()[0]);
            Object[] data = new Object[1];
            data[0] = vahicule;
            h.setHttpRetour(h, 200, "Ok", data);
        }
        return h;
    }
}
