/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classeViews.springboot;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import static dao.Dao.dao;
import java.sql.Date;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "KilometrageVehicule")
public class KilometrageVehicule {
    @AnnotationField(attribut = "idAdmin")
    int idAdmin;
    @AnnotationField(attribut = "idVehicule")
    int idVehicule;
    @AnnotationField(attribut = "matricule")
    String matricule;
    @AnnotationField(attribut = "marque")
    String marque;
    @AnnotationField(attribut = "modele")
    String modele;
    @AnnotationField(attribut = "dateKilometrage")
    Date dateKilometrage;
    @AnnotationField(attribut = "debut")
    int debut;
    @AnnotationField(attribut = "fin")
    int fin;

    public KilometrageVehicule() {
    }

    public KilometrageVehicule(int idAdmin, int idVehicule, String matricule, String marque, String modele, Date datekilometrage, int debut, int fin) {
        this.idAdmin = idAdmin;
        this.idVehicule = idVehicule;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.dateKilometrage = datekilometrage;
        this.debut = debut;
        this.fin = fin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Date getDateKilometrage() {
        return dateKilometrage;
    }

    public void setDateKilometrage(Date dateKilometrage) {
        this.dateKilometrage = dateKilometrage;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
    
    public KilometrageVehicule[] read(KilometrageVehicule vehicule) throws Exception {
        KilometrageVehicule[] listeVehicule = null;
        Object[] objet = null;
        int i=0;
        try {
            objet = dao().select(vehicule, null);
            listeVehicule = new KilometrageVehicule[objet.length];
            for(i=0; i<objet.length; i++) {
                listeVehicule[i] = (KilometrageVehicule) objet[i];
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeVehicule;
    }
}
