package Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * This class handles token creation and parsing
 * @date 10/21/2021
 * @author Kollier Martin and Erika Johnson
 **/

public class RequestArgChecker {
    private static String paramName, paramValue;
    private static String[] paramValues;
    private static Enumeration<String> parameterNames;

    public static String[] handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String[] parameterAndArg = new String[2];
        parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            paramName = parameterNames.nextElement();
            parameterAndArg[0] = paramName;

            paramValues = req.getParameterValues(paramName);

            if(paramValues.length > 1) {
                for (String value : paramValues) {
                    paramValue = value;
                    System.out.println(paramValue);
                }
            } else {
                parameterAndArg[1] = paramValues[0];
            }
        }

        return parameterAndArg;
    }

    public static void printRequestParam(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("text/plain");

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            System.out.println(paramName);

            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println(paramValue);
            }

        }
    }
}
