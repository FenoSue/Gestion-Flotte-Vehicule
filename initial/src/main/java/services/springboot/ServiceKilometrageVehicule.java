/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classeViews.springboot.KilometrageVehicule;
import classes.springboot.HttpRetour;

/**
 *
 * @author USER
 */
public class ServiceKilometrageVehicule {
    HttpRetour h = new HttpRetour();
    KilometrageVehicule kv = new KilometrageVehicule();
    
    public HttpRetour getKilometrageVehicule(int idVehicule) throws Exception {
        if(idVehicule!=0) {
            KilometrageVehicule vehicule = new KilometrageVehicule();
            vehicule.setIdVehicule(idVehicule);
            KilometrageVehicule[] kilometrage = kv.read(vehicule);
            h.setHttpRetour(h, 200, "Ok", kilometrage);
        }
        else {
            h.setHttpRetour(h, 505, "Erreur", null);
        }
        return h;
    }
}
