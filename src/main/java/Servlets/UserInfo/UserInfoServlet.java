package Servlets.UserInfo;

import Services.UserInfoService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(202);
        resp.setContentType("application/json");
        String param;
        JSONObject jOBj = new JSONObject();

        while (req.getParameterNames().hasMoreElements()){
            param = req.getParameterNames().nextElement();

            switch (req.getParameter(param)) {
                case "all":
                    jOBj.put("User Info", UserInfoService.getAllUserInfo());
                    resp.getWriter().print(jOBj);
                    break;
                default:
                    jOBj.put("Requested User", UserInfoService.getUserInfoByUUID(UUID.fromString(param)));
                    resp.getWriter().print(jOBj);
                    break;
            }
        }
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            out.write(paramName);
            out.write("n");

            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                out.write("t" + paramValue);
                out.write("n");
            }

        }

        out.close();
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
