package Servlets;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/users/")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(202);
        response.setContentType("application/json");
        String param;
        JSONObject jOBj = new JSONObject();

        while (request.getParameterNames().hasMoreElements()){
            param = request.getParameterNames().nextElement();

            switch (request.getParameter(param)) {
                case "all":
                    //jOBj.put("Users", UserService.getAllUsers());
                    response.getWriter().print(jOBj);
                    break;
                default:
                    //jOBj.put("Requested User", UserService.getUserByFirstName(param));
                    response.getWriter().print(jOBj);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
