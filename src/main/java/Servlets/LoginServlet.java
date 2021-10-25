package Servlets;

import Logging.MyLogger;
import Services.LoginService;
import Services.UserService;
import Utils.JWTUtil;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/login", "/login?"})
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

        response.setStatus(202);
        response.setContentType("application/json");

        if (LoginService.validate(request.getParameter("username"), request.getParameter("password"))) {
            // Parse token for validation
            if (JWTUtil.parseJWT(jwt)) {
                // Accept input and add information to Object
                jObj.put("Validation", jwt);
                jObj.put("User Exists", true);
                jObj.put("Role of User", UserService.getRoleID(request.getParameter("username")));

                try {
                    response.getWriter().write(jObj.toString());
                } catch (IOException e) {
                    MyLogger.getMyLogger().writeLog(e.toString(), 3);
                }
            }
        } else {
            jObj.put("User Exists", false);

            try {
                response.getWriter().write(jObj.toString());
            } catch (IOException e) {
                MyLogger.getMyLogger().writeLog(e.toString(), 3);
            }
        }
    }
}
