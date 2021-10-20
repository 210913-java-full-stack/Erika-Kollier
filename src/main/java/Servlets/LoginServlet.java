package Servlets;

import Services.LoginService;
import Utils.JWTUtil;
import Utils.RequestArgChecker;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@WebServlet(name = "LoginServlet", value = {"/login", "/login?username", "/*&password"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Request successful
        response.setStatus(202);
        response.setContentType("application/json");
        JSONObject jOBj = new JSONObject();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jObj = new JSONObject();

        String jwt = JWTUtil.createJWT(request);
        RequestArgChecker.printRequestParam(request, response);

        if (JWTUtil.parseJWT(jwt) && LoginService.validate(request.getParameter("username"), request.getParameter("password")))
            request.login(request.getParameter("username"), request.getParameter("password"));

        // Parse token for validation
        if (JWTUtil.parseJWT(jwt)){
            // Accept input and add information to Object
            InputStream requestBody = request.getInputStream();
            Scanner s = new Scanner(requestBody).useDelimiter("\\A");
            String body = s.hasNext() ? s.next() : "";
            jObj.put("Tokenized User", body + jwt);
            response.getWriter().write(jObj.toString());
        }
    }
}
