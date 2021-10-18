package Servlets;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserInfoServlet", value = "/userInfo")
public class UserInfoServlet extends HttpServlet {
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
                    //jOBj.put("User Info", UserInfoService.getAllUserInfo());
                    response.getWriter().print(jOBj);
                    break;
                default:
                    //jOBj.put("Requested User", UserInfoService.getUserInfoByUUID(UUID.fromString(param)));
                    response.getWriter().print(jOBj);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // For single updates
    }
}
