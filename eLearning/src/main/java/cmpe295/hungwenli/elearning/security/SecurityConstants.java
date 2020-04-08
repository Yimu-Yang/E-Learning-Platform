package cmpe295.hungwenli.elearning.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTSecretKeyToGenJWTSecretKeyToGenJWT";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/signup";
    public static final String SIGN_IN_URL = "/signin";
    public static final String COURSE_URL = "/course/**";
}
