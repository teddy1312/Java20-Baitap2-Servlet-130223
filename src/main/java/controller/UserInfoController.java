package controller;

import model.UserModel;
import service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserInfoController", urlPatterns = "/info")
public class UserInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");

        UserInfoService userInfoService = new UserInfoService();
        UserModel userModel = userInfoService.getUserInfo(email);
        if(userModel != null){
            req.setAttribute("userId",userModel.getId());
            req.setAttribute("email",userModel.getEmail());
            req.setAttribute("pass",userModel.getPassword());
            req.setAttribute("fullname",userModel.getFullname());
            req.setAttribute("phone",userModel.getPhone());
            req.setAttribute("address",userModel.getAddress());
        }

        req.getRequestDispatcher("info.jsp").forward(req,resp);
    }

}
