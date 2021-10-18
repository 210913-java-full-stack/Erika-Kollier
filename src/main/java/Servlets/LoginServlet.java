package Servlets;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Scanner;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Request successful
        // Parse request
        // Store username and password

        // Begin signing
        Key key = Keys.secretKeyFor(SignatureAlgorithm.ES256);

        // Set Subject to be the username given
        String jws = Jwts.builder()
                .setSubject(request.getParameter("param"))
                .signWith(key)
                .compact();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Accept input and add information to Object

        InputStream requestBody = request.getInputStream();
        String jsonText = null;
        Scanner scn = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        jsonText = scn.useDelimiter("\\A").next();

        //GlobalPersistence.getSession().save();
    }

    private boolean parseJWS(Key key, String jwsString) {
        Jws<Claims> jws;
        boolean parsed = false;

        try {
            jws = Jwts.parserBuilder()  // Creates parser instance
                    .setSigningKey(key)         // Specify the key to verify this jws signature
                    .build()                    // Returns a new, thread-safe, parser
                    .parseClaimsJws(jwsString); // Parse the jws and return the original jws

            parsed = true;
        }catch (JwtException e){
            // we *cannot* use the JWT as intended by its creator
            // do something with exception
        }

        return parsed;
    }
}
