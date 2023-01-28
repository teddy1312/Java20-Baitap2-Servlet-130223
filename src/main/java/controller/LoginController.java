package controller;

import com.google.gson.Gson;
import payload.BasicResponse;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("email");
        String password = req.getParameter("password");

        BasicResponse basicResponse = new BasicResponse();
        LoginService loginService = new LoginService();

        if(loginService.checkLogin(account,password)){
            HttpSession session = req.getSession();
            session.setAttribute("email",account);
            session.setMaxInactiveInterval(60*5);

            basicResponse.setStatusCode(500);
            basicResponse.setMessage("Đăng nhập thành công");
            basicResponse.setData(req.getContextPath()+"/info");

            //resp.sendRedirect(req.getContextPath()+"/info");
        } else {
            basicResponse.setStatusCode(200);
            basicResponse.setMessage("Email hoặc mật khẩu không đúng");
            basicResponse.setData(account);
        }

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
