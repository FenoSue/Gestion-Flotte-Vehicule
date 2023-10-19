/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import static dao.Dao.dao;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Vehicule")
public class Vehicule {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "matricule")
    String matricule;
    @AnnotationField(attribut = "marque")
    String marque;
    @AnnotationField(attribut = "modele")
    String modele;

    public Vehicule() {
    }

    public Vehicule(String matricule, String marque, String modele) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public Vehicule getById(int id) throws Exception {
        Vehicule vehicule = new Vehicule();
        Vehicule[] listeVehicule = null;
        int i=0;
        try {
            listeVehicule = this.read();
            for(i=0; i<listeVehicule.length; i++) {
                if(listeVehicule[i].getId()==id) {
                    vehicule = listeVehicule[i];
                    break;
                }
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return vehicule;
    }
    
    public void create(String matricule, String marque, String modele) throws Exception {
        try {
            Vehicule vehicule = new Vehicule(matricule, marque, modele);
            dao().insert(vehicule, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public Vehicule[] read() throws Exception {
        Vehicule[] listeVehicule = null;
        Object[] objet = null;
        int i=0;
        try {
            objet = dao().select(this, null);
            listeVehicule = new Vehicule[objet.length];
            for(i=0; i<objet.length; i++) {
                listeVehicule[i] = (Vehicule) objet[i];
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeVehicule;
    }
    
    public void update(Vehicule vehicule, Vehicule listeVehicule) throws Exception {
        try {
            dao().update(vehicule, listeVehicule, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void delete(Vehicule vehicule) throws Exception {
        try {
            dao().delete(vehicule, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    } 
}
