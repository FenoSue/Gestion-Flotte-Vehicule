/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import annotations.AnnotationClass;
import annotations.AnnotationField;
import static dao.Dao.dao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author USER
 */
@AnnotationClass(table = "Token")
public class Token {
    public static final long expiration = 86400;
    public static final String keyToken = "Token22";
    
    @AnnotationField(attribut = "id")
    int id;
    @AnnotationField(attribut = "utilisateur")
    int utilisateur;
    @AnnotationField(attribut = "token")
    String token;
    @AnnotationField(attribut = "dateExpiration")
    Date dateExpiration;

    public Token() {
    }

    public Token(int utilisateur, String token, Date dateExpiration) {
        this.utilisateur = utilisateur;
        this.token = token;
        this.dateExpiration = dateExpiration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void create(Token tokenGenerer) throws Exception {
        try {
            dao().insert(tokenGenerer, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public Token readByToken(String tokenUtilisateur) throws Exception {
        Token tokenAdmin = new Token();
        Token token = new Token();
        Object[] objet = null;
        int i=0;
        try {
            token.setToken(tokenUtilisateur);
            objet = dao().select(token, null);
            for(i=0; i<objet.length; i++) {
                tokenAdmin = (Token) objet[i];
            }
        }
        catch(Exception exception) {
            throw exception;
        }
        return tokenAdmin;
    }
    
    public void update(Token token, Token newToken) throws Exception {
        try {
            dao().update(token, newToken, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public void delete(Token token) throws Exception {
        try {
            dao().delete(token, null);
        }
        catch(Exception exception) {
            throw exception;
        }
    }
    
    public String genererToken(int utilisateurId) throws Exception {
        Token tokenGenerer = new Token();
        long now = System.currentTimeMillis();
        int expire = (int) this.expiration/60/60/24;
        Date date = Date.valueOf(LocalDate.now().plusDays(expire));
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Token.keyToken).setIssuedAt(new Date(now)).setExpiration(date).claim("idUtilisateur", utilisateurId).compact();
        tokenGenerer.setUtilisateur(utilisateurId);
        tokenGenerer.setToken(token);
        tokenGenerer.setDateExpiration(date);
        create(tokenGenerer);
        return token;
    }
}