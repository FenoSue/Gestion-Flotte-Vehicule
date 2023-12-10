package com.example.springboot;

import classes.springboot.Admin;
import classes.springboot.HttpRetour;
import classes.springboot.Token;
import java.sql.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.springboot.ServiceAdmin;
import services.springboot.ServiceKilometrage;
import services.springboot.ServiceKilometrageVehicule;
import services.springboot.ServiceVehicule;

@RestController
public class HelloController {

    private Token token = new Token();
    ServiceVehicule sv = new ServiceVehicule(); 
    ServiceKilometrage sk = new ServiceKilometrage();
    ServiceAdmin sa = new ServiceAdmin();
    ServiceKilometrageVehicule skv = new ServiceKilometrageVehicule();
    
    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public String test() throws Exception {
        return "ok insert Vehicule";
    }
    
    /* INSERTION DE VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/AddVehicule")
    public HttpRetour insertVoiture(@RequestHeader(name = "Authorization") String token, @RequestParam String matricule, @RequestParam String marque, @RequestParam String modele) 
            throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour vehicule = sv.insertVehicule(realToken, matricule, marque, modele);
        return vehicule;
    }
    
    /* LISTE DES VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/ShowVehicule")
    public ResponseEntity<HttpRetour> listeVehicule(@RequestHeader(name = "Authorization") String token) throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour vehicule = sv.listeVehicule(realToken);
        ResponseEntity<HttpRetour> response = new ResponseEntity<>(vehicule, HttpStatus.OK);
        return response;
    }
    
    /* UPDATE MATRICULE VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/UpdateVehicule")
    public HttpRetour updateVehicule(int idVehicule, String matricule) throws Exception {
        HttpRetour update = sv.updateVehicule(idVehicule, matricule);
        return update;
    }
    
    /* DELETE UN VEHICULE */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DropVehicule")
    public HttpRetour deleteVehicule(int idVehicule) throws Exception {
        HttpRetour delete = sv.deleteVehicule(idVehicule);
        return delete;
    }
    
    /* INSERTION DE KILOMETRAGE D'UN VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/AddKilometrage")
    public HttpRetour insertKilometrage(@RequestHeader(name = "Authorization") String token, int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) 
            throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour kilometrage = sk.insertKilometrage(realToken, idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
        return kilometrage;
    }
    
    /* GET KILOMETRAGE D'UN VEHICULE */
    @CrossOrigin(origins = "*")
    @GetMapping("/ShowKilometrageVehicule")
    public ResponseEntity<HttpRetour> getKilometrageVehicule(@RequestHeader(name = "Authorization") String token, @RequestParam String idVehicule) throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour kilometrage = skv.getKilometrageVehicule(realToken, idVehicule);
        ResponseEntity<HttpRetour> response = new ResponseEntity<>(kilometrage, HttpStatus.OK);
        return response;
    }
    
    /* LISTE DES KILOMETRAGES */
    @CrossOrigin(origins = "*")
    @PostMapping("/ShowKilometrage")
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
        HttpRetour connection = sa.connection(admin.getLogin(), admin.getPwd());
        return connection;
    }
    
    /* DECONNECTION */
    @CrossOrigin(origins = "*")
    @GetMapping("/Deconnection")
    public HttpRetour deconnection(@RequestHeader(name = "Authorization") String token) throws Exception {
        String realToken = token.substring(7, token.length());
        HttpRetour deconnection = sa.deconnection(realToken);
        return deconnection;
    }
}
