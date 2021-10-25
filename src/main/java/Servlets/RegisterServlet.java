package Servlets;

import Logging.MyLogger;
import POSTModels.RegisterInfo;
import Utils.JWTUtil;
import Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    /**
     * Handles the request for user registration
     * @param request Request from client
     * @param response Response to client
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        JSONObject jObj = new JSONObject();
        InputStream requestBody;

        try {
            requestBody = request.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();

            ObjectMapper mapper = new ObjectMapper();
            RegisterInfo newUser = mapper.readValue(jsonText, RegisterInfo.class);

            UserService.register(newUser.getFirstname(),
                    newUser.getLastname(), newUser.getUsername(),
                    newUser.getPassword());

            jObj.put("Token", JWTUtil.createJWT(request));
            response.setStatus(202);
            response.setContentType("application/json");
            response.getWriter().write(jObj.toString());
        } catch (IOException e) {
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }
}
