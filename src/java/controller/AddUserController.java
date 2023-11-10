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
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.User;
import validate.Validate;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "AddUserController", urlPatterns = {"/add-user"})
@MultipartConfig(maxFileSize = 16177215)
public class AddUserController extends HttpServlet {

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
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        if (user.getRole() != 0) {
            request.getRequestDispatcher("NoPermission.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("AddUser.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String role_raw = request.getParameter("role");
        String creatat_raw = request.getParameter("creatat");
        String avatar = request.getParameter("image");
        String phone = request.getParameter("phone");
        try {
            int role = Integer.parseInt(role_raw);
            Date creatat = Date.valueOf(creatat_raw);
            UserDao dao = new UserDao();

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
            User checkEmail = dao.checkAddEmail(email);
            User checkUsername = dao.checkAddUsername(username);
            User checkPhone = dao.checkAddPhone(phone);
            if (checkEmail != null || checkUsername != null || checkPhone != null) {
                if (checkEmail != null) {
                    response.getWriter().print("existemail");
                }
                if (checkUsername != null) {
                    response.getWriter().print("existusername");
                }
                if (checkPhone != null) {
                    response.getWriter().print("existphone");
                }
                return;
            } 
            dao.addUser(email, password, username, fullname, address, avatar, role, creatat, phone);
            response.getWriter().print("OK");
        } catch (Exception e) {
        }
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
