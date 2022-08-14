package com.example.barang.util.Jwt;

import com.example.barang.persistence.dao.ReturnsAuthDao;
import com.example.barang.persistence.domain.ReturnsAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtil  implements Serializable {
    private static final Logger logger = LogManager.getLogger(JwtTokenUtil.class);
    private static final long serialVersionUID = -2550185165452007488L;

    @Autowired
    private ReturnsAuthDao returnsAuthDao;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; //5 hours

    @Value("${jwt.rahasia}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(ReturnsAuth returnsAuth) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, returnsAuth.getEmail());
    }

    //while creating the token -
    //Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        logger.info("do generate token for users");
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        boolean isElligleToken=  returnsAuthDao.isElligibleToken(token);
        boolean result =  (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && isElligleToken);
        return  result;
    }
}
