package mkos.caos.caos_app.Security;

public class Constants {

    public static final String SIGN_UP_URLS = "/api/auth/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 300000; //30 seconds

}
