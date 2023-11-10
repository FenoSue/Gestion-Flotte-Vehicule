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
    
    public HttpRetour getKilometrageVehicule(int idVehicule) {
        return h;
    }
}
