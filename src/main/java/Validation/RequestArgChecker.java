package Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class RequestArgChecker {
    private static String paramName, paramValue;
    private static String[] paramValues;
    private static Enumeration<String> parameterNames;

    public static void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");

        parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            paramName = parameterNames.nextElement();
            out.write(paramName);

            paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                paramValue = paramValues[i];
                out.write(paramValue);
            }
        }

        out.close();
    }
}
