package Servlets;

import Services.PersistenceService;
import org.json.JSONObject;

import javax.persistence.Parameter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class TrainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
        resp.setContentType("application/json");
        JSONObject jOBj = new JSONObject();

        String param = req.getParameter("param");

        if (param != null) {
            switch (param) {
                case "all":
                    jOBj.put("Trains", PersistenceService.getAllTrains());
                    resp.getWriter().print(jOBj);
                /*default:
                    jOBj.put("Train " + req.getParameter("param"), PersistenceService.getTrainByID(UUID.fromString(param));
                    resp.getWriter().print(jOBj);*/
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
