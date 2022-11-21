package org.ar.colegio.escribanos.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.ar.colegio.escribanos.AppProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    private AppProperties appProperties;

    public JwtTokenUtil(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String doGenerateToken() {

        Map<String, Object> claims = new HashMap<>(){{
            put("role", appProperties.getRole());
        }};

        return Jwts.builder()
                .setIssuer(appProperties.getIssuer())
                .setClaims(claims)
                .setAudience(appProperties.getAudience())
                .setSubject(appProperties.getSubject())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512, appProperties.getSecret())
                .compact();
    }

}