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
@AnnotationClass(table = "Admin")
public class Admin {
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "login")
    String login;
    @AnnotationField(attribut = "pwd")
    String pwd;

    public Admin() {
    }

    public Admin(String login, String pwd) {
        this.login = login;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public Admin getById(int id) throws Exception {
        Admin admin = new Admin();
        Admin[] listeAdmin = null;
        int i=0;
        try {
            listeAdmin = this.read();
            for(i=0; i<listeAdmin.length; i++) {
                if(listeAdmin[i].getId()==id) {
                    admin = listeAdmin[i];
                    break;
                }
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return admin;
    }
    
    public void create(String login, String pwd) throws Exception {
        try {
            Admin admin = new Admin(login, pwd);
            dao().insert(admin, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public Admin[] read() throws Exception {
        Admin[] listeAdmin = null;
        Object[] objet = null;
        int i=0;
        try {
            objet = dao().select(this, null);
            listeAdmin = new Admin[objet.length];
            for(i=0; i<objet.length; i++) {
                listeAdmin[i] = (Admin) objet[i];
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return listeAdmin;
    }
    
    public void update(Admin admin, Admin listeAdmin) throws Exception {
        try {
            dao().update(admin, listeAdmin, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void delete(Admin admin) throws Exception {
        try {
            dao().delete(admin, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    } 
}
