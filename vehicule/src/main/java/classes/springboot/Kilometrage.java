/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import static dao.Dao.dao;
import java.sql.Date;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Kilometrage")
public class Kilometrage {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "idVehicule")
    int idVehicule;
    @AnnotationField(attribut = "dateKilometrage")
    Date dateKilometrage;
    @AnnotationField(attribut = "debutKilometrage")
    int debutKilometrage;
    @AnnotationField(attribut = "finKilometrage")
    int finKilometrage;

    public Kilometrage() {
    }

    public Kilometrage(int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) {
        this.idVehicule = idVehicule;
        this.dateKilometrage = dateKilometrage;
        this.debutKilometrage = debutKilometrage;
        this.finKilometrage = finKilometrage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public Date getDateKilometrage() {
        return dateKilometrage;
    }

    public void setDateKilometrage(Date dateKilometrage) {
        this.dateKilometrage = dateKilometrage;
    }

    public int getDebutKilometrage() {
        return debutKilometrage;
    }

    public void setDebutKilometrage(int debutKilometrage) {
        this.debutKilometrage = debutKilometrage;
    }

    public int getFinKilometrage() {
        return finKilometrage;
    }

    public void setFinKilometrage(int finKilometrage) {
        this.finKilometrage = finKilometrage;
    }
    
    public Kilometrage getById(int id) throws Exception {
        Kilometrage kilometrage = new Kilometrage();
        Kilometrage[] listeKilometrage = null;
        int i=0;
        try {
            listeKilometrage = this.read();
            for(i=0; i<listeKilometrage.length; i++) {
                if(listeKilometrage[i].getId()==id) {
                    kilometrage = listeKilometrage[i];
                    break;
                }
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return kilometrage;
    }
    
    public void create(int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        try {
            Kilometrage kilometrage = new Kilometrage(idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
            dao().insert(kilometrage, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public Kilometrage[] read() throws Exception {
        Kilometrage[] listeKilometrage = null;
        Object[] objet = null;
        int i=0;
        try {
            objet = dao().select(this, null);
            listeKilometrage = new Kilometrage[objet.length];
            for(i=0; i<objet.length; i++) {
                listeKilometrage[i] = (Kilometrage) objet[i];
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeKilometrage;
    }
    
    public void update(Kilometrage kilometrage, Kilometrage listeKilometrage) throws Exception {
        try {
            dao().update(kilometrage, listeKilometrage, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void delete(Kilometrage kilometrage) throws Exception {
        try {
            dao().delete(kilometrage, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }   
}
