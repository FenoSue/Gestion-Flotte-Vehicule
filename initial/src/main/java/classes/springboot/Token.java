/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.springboot;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author USER
 */
public class Token {
    public static final long expiration = 100000000;
    public static final String keyToken = "Token22";
    private Set<String> revokedTokens = new HashSet<>();
    
    int id;
    String token;
    Date dateExpiration;
    int utilisateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public String genererToken(int utilisateurId) {
        long now = System.currentTimeMillis();
        Date date = new Date(now + Token.expiration);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Token.keyToken).setIssuedAt(new Date(now)).setExpiration(date).claim("idUtilisateur", utilisateurId).compact();
        return token;
    }
    
    public Token ReturnToken(int idUtilisateur) {
        String token = new Token().genererToken(utilisateur);
        Claims claim = Jwts.parser().setSigningKey(Token.keyToken).parseClaimsJws(token).getBody();
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setDateExpiration(claim.getExpiration());
        newToken.setUtilisateur(idUtilisateur);
        return newToken;
    }
    
    public Date getDateExpiration(String token) {
        Claims claim = Jwts.parser().setSigningKey(Token.keyToken).parseClaimsJws(token).getBody();
        return claim.getExpiration();
    }
    
    /*public Token ToToken(String token) {
        Token t = new Token();
        Claims claim = Jwts.parser().setSigningKey(Token.keyToken).parseClaimsJws(token).getBody();
        int idUtilisateur = Integer.parseInt(claim.get(key:"idUtilisateur"));
        t.setUtilisateur(idUtilisateur);
        t.setToken(token);
        return t;
    }*/ 

    public void revokeToken(String token) {
        revokedTokens.add(token);
    }

    public boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }
}