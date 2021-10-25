package Servlets;

/**
 * @Description This servlet processes the user HTTP methods to get and return Ticket Info as requested, and if permitted
 * @Authors Kollier Martin and Erika Johnson
 * @Date 10/19/2021
 */

import Logging.MyLogger;
import POSTModels.NewTicket;
import Services.TicketService;
import Utils.RequestArgChecker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@WebServlet(name = "TicketServlet", value = {"/ticket", "/ticket?new"})
public class TicketServlet extends HttpServlet {
    /**
     * This get method returns a single Ticket object, or all Ticket objects based on arguments passed in the HTTP request
     * String array 'paramInfo' contains: paramInfo[0] = parameter, paramInfo[1] = value
     * @param request Request from client
     * @param response Response to client
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setStatus(202);
        response.setContentType("application/json");
        JSONObject jOBj = new JSONObject();
        String[] paramInfo;
        int id = 0;

        try {
            paramInfo = RequestArgChecker.handleRequest(request, response);

            if ("id".equals(paramInfo[0])) {
                try {
                    id = (Integer.parseInt(paramInfo[1]));
                } catch (NumberFormatException e) {
                    MyLogger.getMyLogger().writeLog(e.toString(), 3);
                }

                jOBj.put("Requested Ticket", TicketService.getByID(id));
            } else {
                jOBj.put("All Tickets", TicketService.getAllTickets());
            }

            response.getWriter().print(jOBj);
        } catch (Exception e){
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String[] paramInfo;
        InputStream requestBody = null;

        response.setStatus(202);

        // Create ticket and assign it to User
        try {
            paramInfo = RequestArgChecker.handleRequest(request, response);

            if ("new".equals(paramInfo[0])) {
                try {
                    requestBody = request.getInputStream();
                    Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
                    String jsonText = sc.useDelimiter("\\A").next();

                    ObjectMapper mapper = new ObjectMapper();
                    NewTicket newTicket = mapper.readValue(jsonText, NewTicket.class);

                    TicketService.create(newTicket.getTickets(), newTicket.getDestCity(),
                            newTicket.getCurrentCity(), newTicket.getDeparture(), newTicket.getArrival());

                    response.getWriter().write("Route creation complete.");
                } catch (IOException e) {
                    MyLogger.getMyLogger().writeLog(e.toString(), 3);
                }
            } else if ("purchase".equals(paramInfo[0])) {

            } else {

            }
        } catch (Exception e){
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }
}
