package Servlets;

import Services.PersistenceService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UserIDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
        resp.setContentType("application/json");

        String id = req.getParameter("id");
        JSONObject jOBj = new JSONObject();

        if (id != null){
            jOBj.put("Requested User", PersistenceService.getUserByID(UUID.fromString(id)));
            resp.getWriter().print(jOBj);
        }
    }
}
