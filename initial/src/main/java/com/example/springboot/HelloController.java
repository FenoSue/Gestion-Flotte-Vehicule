package com.example.springboot;

import classes.springboot.Admin;
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
    
    @CrossOrigin(origins = "*")
    @GetMapping("/AddVehicule")
    public String insertVoiture(String matricule, String marque, String modele) throws Exception {
        sv.insertVehicule(matricule, marque, modele);
        return "ok insert Vehicule";
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/ShowVehicule")
    public ResponseEntity<Vehicule[]> listeVehicule() throws Exception {
        Vehicule[] vehicule = sv.listeVehicule();
        ResponseEntity<Vehicule[]> response = new ResponseEntity<>(vehicule, HttpStatus.OK);
        return response;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/UpdateVehicule")
    public String updateVehicule(int idVehicule, String matricule) throws Exception {
        sv.updateVehicule(idVehicule, matricule);
        return "ok update Vehicule";
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DropVehicule")
    public String deleteVehicule(int idVehicule) throws Exception {
        sv.deleteVehicule(idVehicule);
        return "ok delete Vehicule";
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/AddKilometrage")
    public String insertKilometrage(int idVehicule, Date dateKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        sk.insertKilometrage(idVehicule, dateKilometrage, debutKilometrage, finKilometrage);
        return "ok insert Kilometrage";
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/ShowKilometrage")
    public ResponseEntity<Kilometrage[]> listeKilometrage() throws Exception {
        Kilometrage[] kilometrage = sk.listeKilometrage();
        ResponseEntity<Kilometrage[]> response = new ResponseEntity<>(kilometrage, HttpStatus.OK);
        return response;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/UpdateKilometrage")
    public String updateKilometrage(int idKilometrage, int debutKilometrage, int finKilometrage) throws Exception {
        sk.updateKilometrage(idKilometrage, debutKilometrage, finKilometrage);
        return "ok update Vehicule";
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/DropKilometrage")
    public String deleteKilometrage(int idKilometrage) throws Exception {
        sk.deleteKilometrage(idKilometrage);
        return "ok delete Vehicule";
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/Connection")
    public String connection(@RequestBody Admin admin) throws Exception {
        String resultat = "non-connecté";
        boolean connection = sa.Connection(admin.getLogin(), admin.getPwd());
        if(connection==true) {
            try {
                String genererToken = token.genererToken(admin.getId());
                resultat = "Voilà le Token : "+genererToken;
            }
            catch(Exception ex) {
                resultat = "non-connecté";
            }
        }
        return resultat;
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/Deconnection")
    public String deconnection(@RequestBody Admin admin) {
            Token t = token.ReturnToken(admin.getId());
            String tokenGet = t.getToken();
            token.revokeToken(tokenGet);
            return "deconnecté: "+tokenGet;
    }
}
