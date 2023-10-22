package com.portmz.portsystem.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.portmz.portsystem.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    //Para criar um token
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername()) //vamos pegar o usuario
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    //GENERATETOKENSERVICE
    // Método para gerar refreshToken aleatório
    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }



    //Passo 2 - para validar o token. Vamos pegar o login que salvamos no token
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api") //quem foi o emmissor
                    .build()
                    .verify(token) //verificar o token
                    .getSubject(); //
        } catch (JWTVerificationException exception){ //tratar a excesso
            return "";
        }
    }

    //tempo que vai expirar. Tempo de expiracao vai ser duas horas;
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
