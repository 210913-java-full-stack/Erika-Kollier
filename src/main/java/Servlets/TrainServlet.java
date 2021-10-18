package Servlets;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrainServlet", value = "/train")
public class TrainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(202);
        response.setContentType("application/json");
        JSONObject jOBj = new JSONObject();

        String param = request.getParameter("param");

        if (param != null) {
            switch (param) {
                case "all":
                    //jOBj.put("Trains", TrainService.getAllTrains());
                    response.getWriter().print(jOBj);
                default:
                    //jOBj.put("Train " + req.getParameter("param"), PersistenceService.getTrainByID(UUID.fromString(param));
                    response.getWriter().print(jOBj);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
