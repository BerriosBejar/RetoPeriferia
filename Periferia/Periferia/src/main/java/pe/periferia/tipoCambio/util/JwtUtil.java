package pe.periferia.tipoCambio.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.periferia.tipoCambio.config.JwtConfig;

import java.util.Date;

@Component
public class JwtUtil {
    @Autowired
    private JwtConfig jwtConfig;


    public String generateJwtToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtConfig.getJwtExpiration() * 1000);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(jwtConfig.getJwtAlgorithm(), jwtConfig.secretKey())
                .compact();
    }
    public boolean validateJwtToken(String token, String username) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtConfig.secretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.secretKey())
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

}
