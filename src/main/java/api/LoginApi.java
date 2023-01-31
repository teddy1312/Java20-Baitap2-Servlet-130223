package api;

import com.google.gson.Gson;
import payload.BasicResponse;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginApi",urlPatterns = {"/api/login"})
public class LoginApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        BasicResponse basicResponse = checkLogin(req,email,password);

        toJson(resp,basicResponse);
    }

    private BasicResponse checkLogin(HttpServletRequest req, String email, String password){
        BasicResponse basicResponse = new BasicResponse();
        LoginService loginService = new LoginService();

        if(loginService.checkLogin(email,password)){
            HttpSession session = req.getSession();
            session.setAttribute("email",email);
            session.setMaxInactiveInterval(60*5);

            basicResponse.setStatusCode(500);
            basicResponse.setMessage("Đăng nhập thành công");
            basicResponse.setData(req.getContextPath()+"/info");
        } else{
            basicResponse.setStatusCode(200);
            basicResponse.setMessage("Đăng nhập thất bại");
            basicResponse.setData(email);
        }

        return basicResponse;
    }

    private void toJson(HttpServletResponse resp, BasicResponse basicResponse) throws IOException {
        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }
}
