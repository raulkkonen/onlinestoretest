package academy.digitallab.store.security.auth;

import academy.digitallab.store.security.auth.util.Constant;
import academy.digitallab.store.security.auth.util.PemUtils;
import academy.digitallab.store.security.auth.util.RSAAlgorithm;
import academy.digitallab.store.security.domain.dto.TokenRedisDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import  org.apache.commons.lang3.StringUtils ;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;

public class TokenAuthenticationService {

    static RSAPrivateKey privateKey;
    static RSAPublicKey publicKey;

    static void addAuthentication(HttpServletResponse res, String username, List<GrantedAuthority> authorities) {

        String token= StringUtils.EMPTY;
        List<String> authoritiesS = authorities.stream().
                map ( authority -> authority.getAuthority ())
                .collect ( Collectors.toList());

        try {
            privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(Constant.PRIVATE_KEY_FILE_RSA, "RSA");
            publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(Constant.PUBLIC_KEY_FILE_RSA, "RSA");
            token = JWT.create()
                    .withSubject(username)
                    .withJWTId(UUID.randomUUID().toString() )
                    .withKeyId( Constant.KEY_ID_JWT)
                    .withAudience(Constant.KEY_AUDIENCE)
                    .withNotBefore(new Date())
                    .withIssuer(Constant.KEY_ISSUER)
                    .withExpiresAt(new Date(System.currentTimeMillis() + Constant.EXPIRATIONTIME))
                    .withArrayClaim("authorities",authoritiesS.stream().toArray(String[]::new) )
                    .sign(Algorithm.RSA256(publicKey, privateKey)
                    );

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        res.addHeader( Constant.TOKEN_HEADER_STRING,  Constant.TOKEN_PREFIX + token);

    }


    static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(Constant.TOKEN_HEADER_STRING);
        List<GrantedAuthority>authorities = new ArrayList<>();
        RSAKey key;
        if (token != null) {
            // parse the token.
            String user=null;
            try {

                //privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(Constant.PRIVATE_KEY_FILE_RSA, "RSA");
                publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(Constant.PUBLIC_KEY_FILE_RSA, "RSA");

                RSAKeyProvider provider = RSAAlgorithm.providerForKeys(publicKey, privateKey);

                DecodedJWT jwt = JWT.require(Algorithm.RSA256 ( provider ) )
                        .build()
                        .verify(token.replace(Constant.TOKEN_PREFIX, StringUtils.EMPTY));
                user =jwt.getSubject();
                authorities = Arrays.stream( jwt.getClaim("authorities").asArray(String.class))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

            }catch (JWTDecodeException e){
                System.out.println(e.getMessage());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                return user != null ?
                        new UsernamePasswordAuthenticationToken(user, null,authorities) : null;
            }


        }
        return null;
    }

    static String getToken( HttpServletRequest request){
        String token = request.getHeader( Constant.TOKEN_HEADER_STRING);
        return  token;
    }


    public static TokenRedisDto getExpireToken(String token) {
        RSAKey key;
        if (token != null) {
            // parse the token.
            TokenRedisDto tokenRedis =new TokenRedisDto ();
            try {

                privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(Constant.PRIVATE_KEY_FILE_RSA, "RSA");
                publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(Constant.PUBLIC_KEY_FILE_RSA, "RSA");

                RSAKeyProvider provider = RSAAlgorithm.providerForKeys(publicKey, privateKey);

                DecodedJWT jwt = JWT.require(Algorithm.RSA256 ( provider ) )
                        .build()
                        .verify(token.replace(Constant.TOKEN_PREFIX, StringUtils.EMPTY));
                tokenRedis.setUsername (  jwt.getSubject());
                tokenRedis.setDateTime (  jwt.getExpiresAt ().getTime () );

                return tokenRedis;

            }catch (JWTDecodeException   e){
                System.out.println(e.getMessage());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return null;
    }

}
