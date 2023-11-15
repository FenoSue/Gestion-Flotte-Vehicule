/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.springboot;

import classeViews.springboot.KilometrageVehicule;
import classes.springboot.HttpRetour;
import classes.springboot.Token;

/**
 *
 * @author USER
 */
public class ServiceKilometrageVehicule {
    HttpRetour h = new HttpRetour();
    KilometrageVehicule kv = new KilometrageVehicule();
    
    public HttpRetour getKilometrageVehicule(String token, String idVehicule) throws Exception {
        int idA = 0;
        int idV = 0;
        if(token!=null) {
            idA = new Token().readByToken(token).getUtilisateur();
            System.out.println("idAdmin : "+idA);
            if(idVehicule!=null) {
                idV = Integer.parseInt(idVehicule);
                System.out.println("idAdmin : "+idV);
                KilometrageVehicule vehicule = new KilometrageVehicule();
                vehicule.setIdAdmin(idA);
                vehicule.setIdVehicule(idV);
                KilometrageVehicule[] kilometrage = kv.read(vehicule);
                h.setHttpRetour(h, 200, "Ok", kilometrage);
            }
            else {
                h.setHttpRetour(h, 505, "Erreur", null);
            }
        }
        else {
            String[] data = new String[1];
            data[0] = "Vous devez vous connectez";
            h.setHttpRetour(h, 505, "Erreur", data);
        }
        return h;
    }
}
