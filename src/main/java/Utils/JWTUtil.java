package Utils;

import Logging.MyLogger;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;

/**
 * This class handles token creation and parsing
 * @date 10/23/2021
 * @author Kollier Martin and Erika Johnson
 **/

public class JWTUtil {
    private static Key key;

    private static void createKey(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public static String createJWT(HttpServletRequest request){
        createKey();

        // Build the java web token
        String jwt = Jwts.builder().setIssuer(request.getRequestURL().toString())
                .setSubject(request.getParameter("username"))
                .signWith(key)
                .compact();

        return jwt;
    }

    public static boolean parseJWT(String jwsString) {
        boolean parsed = false;

        try {
            Jws<Claims> jws = Jwts.parserBuilder()  // Creates parser instance
                    .setSigningKey(key)             // Specify the key to verify this jws signature
                    .build()                        // Returns a new, thread-safe, parser
                    .parseClaimsJws(jwsString);     // Parse the jws and return the original jws

            parsed = true;
        }catch (JwtException e){
            // JWT is invalid
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return parsed;
    }
}
