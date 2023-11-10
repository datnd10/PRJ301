/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validate.Validate;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "SignupController", urlPatterns = {"/signup"})
@MultipartConfig(maxFileSize = 16177215)
public class SignupController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Signup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Signup at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        request.getRequestDispatcher("Signup.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        UserDao dao = new UserDao();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Validate v = new Validate();
        if (!v.checkEmail(email) || !v.checkPassword(password) || !v.checkPhone(phone)) {
            if (!v.checkEmail(email)) {
                response.getWriter().print("wrongemail");
            }
            if (!v.checkPassword(password)) {
                response.getWriter().print("wrongpassword");
            }
            if (!v.checkPhone(phone)) {
                response.getWriter().print("wrongphone");
            }
            return;
        }
        if (dao.checkAddEmail(email) != null || dao.checkAddPhone(phone) != null || dao.checkAddUsername(username) != null || (!password.equals(confirmpassword))) {
            if (dao.checkAddEmail(email) != null) {
                response.getWriter().print("existemail");
            }
            if (dao.checkAddUsername(username) != null) {
                response.getWriter().print("existusername");
            }
            if (dao.checkAddPhone(phone) != null) {
                response.getWriter().print("existphone");
            }
            if (!password.equals(confirmpassword)) {
                response.getWriter().print("wrongconfirm");
            }
            return;
        } 
        dao.addUser(email, password, username, "", address, "defaultavatar.png", 2, date, phone);
        response.getWriter().print("OK");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
