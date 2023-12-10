/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token.springboot;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 * @author USER
 */
@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret; // Votre clé secrète

    @Value("${jwt.expiration}")
    private Long expiration; // Durée de validité du token en millisecondes

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return "Bearer " + Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}