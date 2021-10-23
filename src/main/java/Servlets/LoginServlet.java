package Servlets;

import Logging.MyLogger;
import Utils.JWTUtil;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login", "/login?username", "/*&password"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Request successful
        response.setStatus(202);
        response.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jObj = new JSONObject();

        String jwt = JWTUtil.createJWT(request);

        // Parse token for validation
        if (JWTUtil.parseJWT(jwt)){
            // isValid -> continue
            response.setStatus(202);
            response.setContentType("application/json");

            // Accept input and add information to Object
            jObj.put("Requested Token", jwt);

            try {
                response.getWriter().write(jObj.toString());
            } catch (IOException e) {
                MyLogger.getFileLogger().severe(e.toString());
            }
        }
    }
}
