package academy.digitallab.store.shopping.auth;


import academy.digitallab.store.shopping.auth.util.Constant;
import academy.digitallab.store.shopping.auth.util.PemUtils;
import academy.digitallab.store.shopping.auth.util.RSAAlgorithm;
import academy.digitallab.store.shopping.dto.TokenRedisDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthenticationService {


    static RSAPublicKey publicKey;



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

                RSAKeyProvider provider = RSAAlgorithm.providerForKeys(publicKey, null);

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
                        new UsernamePasswordAuthenticationToken(user, token,authorities) : null;
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


                publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(Constant.PUBLIC_KEY_FILE_RSA, "RSA");

                RSAKeyProvider provider = RSAAlgorithm.providerForKeys(publicKey, null);

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
