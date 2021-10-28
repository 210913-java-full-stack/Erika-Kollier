package Servlets;

import Logging.MyLogger;
import Models.Train;
import POSTModels.RouteInfo;
import Services.TrainService;
import Utils.JWTUtil;
import Utils.RequestArgChecker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This servlet processes the user HTTP methods to get and return Train Info as requested, and if permitted
 * @author Kollier Martin and Erika Johnson
 * @date 10/23/2021
 */

@WebServlet(name = "TrainServlet", value = {"/train", "/train?"})
public class TrainServlet extends HttpServlet {
    private String[] paramInfo = {"", ""};

    /**
     * This get method returns a single Train's Information, all Train's Information, or all passengers with a ticket based on arguments passed in the HTTP request
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

        try {
            paramInfo = RequestArgChecker.handleRequest(request, response);
        } catch (Exception e) {
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        if ("id".equals(paramInfo[0])) {
            try {
                id = (Integer.parseInt(paramInfo[1]));
            } catch (NumberFormatException e) {
                MyLogger.getMyLogger().writeLog(e.toString(), 3);
            }

            jOBj.put("Requested Train's Information", TrainService.getTrainByID(id));

        } else if ("passengers".equals(paramInfo[0])) {
            try {
                ArrayList passengers = TrainService.getAllPassengers();

                if (passengers != null) {
                    response.setStatus(200);
                    jOBj.put("List", passengers);
                } else {
                    response.setStatus(500);
                    jOBj.put("Status", "Fetching passengers has failed");
                }
            } catch (Exception e) {
                MyLogger.getMyLogger().writeLog(e.toString(), 3);
            }
        } else {
            jOBj.put("All Trains", TrainService.getAllTrains());
        }

        response.getWriter().print(jOBj);
    }

    /**
     * Every POST request is handled in this method. It changes data within the server
     * @param request Client Request
     * @param response Response to Client
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jObj = new JSONObject();
        InputStream requestBody;
        String token = "";

        try {
            paramInfo = RequestArgChecker.handleRequest(request, response);
        } catch (Exception e) {
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        try {
            token = request.getHeader("Authorization");

            if (JWTUtil.parseJWT(token)) {

                if ("create".equals(paramInfo[0])) {
                    requestBody = request.getInputStream();
                    Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
                    String jsonText = sc.useDelimiter("\\A").next();

                    ObjectMapper mapper = new ObjectMapper();
                    RouteInfo newRoute = mapper.readValue(jsonText, RouteInfo.class);

                    // Do newRoute thing here
                    int newTrainID = TrainService.createRoute(newRoute.getDepartureStation(),
                            newRoute.getArrivalStation(), newRoute.getDepartureDate(),
                            newRoute.getArrivalDate());

                    jObj.put("Status", "Processed");
                    jObj.put("New Train ID", newTrainID);
                    response.setStatus(202);
                    response.setContentType("application/json");
                } else {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    jObj.put("Status", "Unauthorized Access");
                }

                response.getWriter().write(jObj.toString());
            }
        } catch (Exception e) {
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * Every DELETE request is handled in this method. It deletes data within the server
     * @param request Client Request
     * @param response Response to Client
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jObj = new JSONObject();

        try {
            paramInfo = RequestArgChecker.handleRequest(request, response);

            if ("delete".equals(paramInfo[0])) {
                try {
                    Train train = TrainService.getTrainByID(Integer.parseInt(request.getHeader("ID")));
                    System.out.println(train.toString());

                    if(TrainService.delete(train)){
                        jObj.put("Status", "Train has failed to delete...");
                        response.setStatus(500);
                    } else {
                        jObj.put("Status", "Train has been deleted...");
                        response.setStatus(202);
                    }

                    response.setContentType("application/json");
                    response.getWriter().write(jObj.toString());
                } catch (Exception e) {
                    MyLogger.getMyLogger().writeLog(e.toString(), 3);
                }
            }
        } catch (Exception e) {
            MyLogger.getMyLogger().writeLog(e.toString(), 4);
        }
    }
}
