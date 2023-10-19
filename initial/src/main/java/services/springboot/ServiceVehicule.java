/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classes.springboot.Vehicule;

/**
 *
 * @author USER
 */
public class ServiceVehicule {
    Vehicule v = new Vehicule();
    
    public void insertVehicule(String matricule, String marque, String modele) throws Exception {
        v.create(matricule, marque, modele);
    }
    
    public Vehicule getVehicule(int idVehicule) throws Exception {
        Vehicule vehicule = v.getById(idVehicule);
        return vehicule;
    }
    
    public Vehicule[] listeVehicule() throws Exception {
        Vehicule[] vehicule = v.read();
        return vehicule;
    }
    
    public void updateVehicule(int idVehicule, String matricule) throws Exception {
        Vehicule vehicule = this.getVehicule(idVehicule);
        Vehicule newVehicule = new Vehicule();
        newVehicule.setMatricule(matricule);
        v.update(vehicule, newVehicule);
    }
    
    public void deleteVehicule(int idVehicule) throws Exception {
        Vehicule vehicule = this.getVehicule(idVehicule);
        v.delete(vehicule);
    }
}
