package Servlets;

/**
 * @Description This servlet processes the user HTTP methods to get and return Ticket Info as requested, and if permitted
 * @Authors Kollier Martin and Erika Johnson
 * @Date 10/19/2021
 */

import Logging.MyLogger;
import Services.TicketService;
import Utils.RequestArgChecker;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TicketServlet", value = {"/ticket", "/ticket?id", "/ticketPurchase"})
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
                    MyLogger.getFileLogger().severe(e.toString());
                }

                jOBj.put("Requested Ticket", TicketService.getByID(id));
            } else {
                jOBj.put("All Tickets", TicketService.getAllTickets());
            }

            response.getWriter().print(jOBj);
        } catch (Exception e){
            MyLogger.getFileLogger().info(e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        // Create ticket and assign it to User

    }
}
