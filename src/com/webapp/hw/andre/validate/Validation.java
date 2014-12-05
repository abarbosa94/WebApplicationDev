package com.webapp.hw.andre.validate;
/*************************************
*
* Student Name: Andre Barbosa
* Assingment: #4
* Class: CIS 4160
*
*
************************************/
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.hw.andre.DAO.JdbcAdminDao;
import com.webapp.hw.andre.model.Admin;
//validation
//@WebServlet("/schDesign_HTML/AdminSchReader/Validation")
public class Validation extends HttpServlet {
    private String login = null;
    private String password = null;
    private int dept = 0;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        JdbcAdminDao user = new JdbcAdminDao();
        List<Admin> valid = user.getUserPass();
        login = request.getParameter("userId");
        password = request.getParameter("password");
        dept = Integer.parseInt(request.getParameter("deptId"));
        if(validate(valid,user)){
            request.getSession().setAttribute("user", user);
            response.sendRedirect("update.jsp");
        }
        else {
            request.getSession().setAttribute("error", "Username or password are wrong");
            RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
            d.forward(request, response);

        }
    }

    private boolean validate(List<Admin> admin, JdbcAdminDao user) {
        boolean validate = false;
        for(Admin contato: admin) {
            if(contato.getUser().equals(login)&&contato.getPass().equals(password)&&contato.getDept()==dept) {
                /*user.setUser(contato.getUser());
                user.setPass(contato.getPass());
                user.setDept(contato.getDept());*/
                validate = true;
            }
        }
        return validate;
    }
}
