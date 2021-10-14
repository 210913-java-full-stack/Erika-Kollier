package Servlets;

import Models.User;
import Repositories.UserRepo;

import Services.PersistenceService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
        resp.setContentType("application/json");

        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());

        String param = req.getParameter("param");
        JSONObject jOBj = new JSONObject();

        if (param != null) {
            switch (param) {
                case "all":
                    jOBj.put("Users", PersistenceService.getAllUsers());
                    resp.getWriter().print(jOBj);
                default:
                    jOBj.put("Requested User", PersistenceService.getUserByFirstName(param));
                    resp.getWriter().print(jOBj);
            }
        } else {
            jOBj.put("Users", PersistenceService.getAllUsers());
            resp.getWriter().print(jOBj);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // For single updates
        super.doPut(req, resp);
    }
}
