package academy.digitallab.store.shopping.auth.util;

public class Constant {
    public static final String TOKEN_HEADER_STRING = "Authorization";

    public final static String KEY_ID_JWT = "keyonlinestore";
    public final static String KEY_AUDIENCE = "onlinestore";
    public final static String KEY_ISSUER = "security-service";

    public static final String PUBLIC_KEY_FILE_RSA = "/security/rsa-public.pem";

   // public static final String PUBLIC_KEY_FILE_RSA = "src/main/resources/rsa-public.pem";

    public static final String    ALGORIHM_RSA ="RSA";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATIONTIME = 864_000_00; // 1 days
}
