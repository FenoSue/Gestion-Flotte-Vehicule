package com.example.springboot;

import classes.springboot.Admin;
import classes.springboot.HttpRetour;
import classes.springboot.Kilometrage;
import classes.springboot.Token;
import classes.springboot.Vehicule;
import java.sql.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.springboot.ServiceAdmin;
import services.springboot.ServiceKilometrage;
import services.springboot.ServiceVehicule;

@RestController
public class HelloController {

    private Token token = new Token();
    ServiceVehicule sv = new ServiceVehicule(); 
    ServiceKilometrage sk = new ServiceKilometrage();
    ServiceAdmin sa = new ServiceAdmin();
    
    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public String test() throws Exception {
        return "ok insert Vehicule";
    }
    
    /* INSERTION DE VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/AddVehicule")
    public String insertVoiture(String matricule, String marque, String modele) throws Exception {
        sv.insertVehicule(matricule, marque, modele);
        return "ok insert Vehicule";
    }
    
    /* LISTE DES VEHICULE */
    @CrossOrigin(origins = "*")
    @PostMapping("/ShowVehicule")
    public ResponseEntity<HttpRetour> listeVehicule() throws Exception {
        Vehicule[] vehicule = sv.listeVehicule();
        ResponseEntity<Vehicule[]> response = new ResponseEntity<>(vehicule, HttpStatus.OK);
        return response;
    }
    
    /* UPDATE MATRICULE VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/UpdateVehicule")
    public String updateVehicule(int idVehicule, String matricule) throws Exception {
        sv.updateVehicule(idVehicule, matricule);
        return "ok update Vehicule";
    }
    
    /* DELETE UN VEHICULE */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DropVehicule")
    public String deleteVehicule(int idVehicule) throws Exception {
        sv.deleteVehicule(idVehicule);
        return "ok delete Vehicule";
    }
    
    /* INSERTION DE KILOMETRAGE D'UN VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/AddKilometrage")
    public HttpRetour insertKilometrage(int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        HttpRetour kilometrage = sk.insertKilometrage(idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
        return kilometrage;
    }
    
    /* GET KILOMETRAGE AVEC IDKILOMETRAGE */
    @CrossOrigin(origins = "*")
    @GetMapping("/ShowKilometrageById")
    public ResponseEntity<HttpRetour> getKilometrage(int idKilometrage) throws Exception {
        HttpRetour kilometrage = sk.getKilometrage(idKilometrage);
        ResponseEntity<HttpRetour> response = new ResponseEntity<>(kilometrage, HttpStatus.OK);
        return response;
    }
    
    /* GET KILOMETRAGE D'UN VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/ShowKilometrageVehicule")
    public ResponseEntity<HttpRetour> getKilometrageVehicule(int idVehicule) throws Exception {
        HttpRetour kilometrage = sk.getKilometrageVehicule(idVehicule);
        ResponseEntity<HttpRetour> response = new ResponseEntity<>(kilometrage, HttpStatus.OK);
        return response;
    }
    
    /* LISTE DES KILOMETRAGES */
    @CrossOrigin(origins = "*")
    @GetMapping("/ShowKilometrage")
    public ResponseEntity<HttpRetour> listeKilometrage() throws Exception {
        HttpRetour kilometrage = sk.listeKilometrage();
        ResponseEntity<HttpRetour> response = new ResponseEntity<>(kilometrage, HttpStatus.OK);
        return response;
    }
    
    /* UPDATE KILOMETRAGE D'UN  VEHICULE*/
    @CrossOrigin(origins = "*")
    @GetMapping("/UpdateKilometrage")
    public HttpRetour updateKilometrage(int idKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        HttpRetour update = sk.updateKilometrage(idKilometrage, debutKilometrage, finKilometrage);
        return update;
    }
    
    /* DELETE UN KILOMETRAGE */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DropKilometrage")
    public HttpRetour deleteKilometrage(int idKilometrage) throws Exception {
        HttpRetour resultat = sk.deleteKilometrage(idKilometrage);
        return resultat;
    }
    
    /* CONNECTION */
    @CrossOrigin(origins = "*")
    @PostMapping("/Connection")
    public HttpRetour connection(@RequestBody Admin admin) throws Exception {
        HttpRetour connection = sa.Connection(admin.getLogin(), admin.getPwd());
        return connection;
    }
    
    /* DECONNECTION */
    @CrossOrigin(origins = "*")
    @PostMapping("/Deconnection")
    public String deconnection(@RequestBody Admin admin) {
        Token t = token.ReturnToken(admin.getId());
        String tokenGet = t.getToken();
        token.revokeToken(tokenGet);
        return "deconnecté: "+tokenGet;
    }
}
