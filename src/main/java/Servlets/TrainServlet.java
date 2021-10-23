package Servlets;

/**
 * @Description This servlet processes the user HTTP methods to get and return Train Info as requested, and if permitted
 * @Authors Kollier Martin and Erika Johnson
 * @Date 10/19/2021
 */

import Logging.MyLogger;
import Models.Train;
import POSTModels.RouteInfo;
import Services.TrainService;
import Utils.RequestArgChecker;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.ECMAErrors;
import jdk.nashorn.internal.runtime.ParserException;
import jdk.nashorn.internal.runtime.ScriptObject;
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
import java.util.stream.Collectors;

@WebServlet(name = "TrainServlet", value = {"/train", "/train?id"})
public class TrainServlet extends HttpServlet {
    /**
     * This get method returns a single Train's Information, or all Train's Information based on arguments passed in the HTTP request
     * String array 'paramInfo' contains: paramInfo[0] = parameter, paramInfo[1] = value
     * @param request Request from client
     * @param response Response to client
     * @throws ServletException not thrown
     * @throws IOException For input and output exceptions that can occur at runtime
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(202);
        response.setContentType("application/json");
        JSONObject jOBj = new JSONObject();
        int id = 0;
        String[] paramInfo = {"", ""};

        try {
            paramInfo = RequestArgChecker.handleRequest(request, response);
        } catch (Exception e) {
            MyLogger.getFileLogger().info(e.toString());
        }

        if ("id".equals(paramInfo[0])) {
            try {
                id = (Integer.parseInt(paramInfo[1]));
            } catch (NumberFormatException e) {
                MyLogger.getFileLogger().severe(e.toString());
            }

            jOBj.put("Requested Train's Information", TrainService.getTrainByID(id));
        } else {
            jOBj.put("All Trains", TrainService.getAllTrains());
        }

        response.getWriter().print(jOBj);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // When admin sends new Trip Info, create new entities on DB
        JSONObject jObj = new JSONObject();

        InputStream requestBody = null;

        try {
            requestBody = request.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();

            ObjectMapper mapper = new ObjectMapper();
            RouteInfo newRoute = mapper.readValue(jsonText, RouteInfo.class);
            System.out.println("New Route: " + newRoute);
            // Do newRoute thing here
            /*TrainService.createRoute(newRoute.getDepartureCity(),
                    newRoute.getArrivalCity(), newRoute.getStationName(),
                    newRoute.getDepartureDate(), newRoute.getArrivalDate());*/

            jObj.put("Status", "Information Received and Stored...");
            response.setStatus(202);
            response.setContentType("application/json");
            response.getWriter().write(jObj.toString());
        } catch (IOException e) {
            MyLogger.getFileLogger().severe(e.toString());
        }


        // Create Route -> Train, Schedule, Station, Ticket
        //TrainService.createRoute();
    }
}
