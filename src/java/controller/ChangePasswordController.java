/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.sql.Date;
import model.User;
import validate.Validate;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
public class ChangePasswordController extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
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
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        Validate v = new Validate();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (!currentPassword.equals(user.getPassword())) {
            request.setAttribute("errorcurrentpass", "Your current password is not correct!");
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        } else {
            if (!v.checkPassword(newPassword)) {
                request.setAttribute("errornewpass", "Password must be have at least 6 characters and 1 number!");
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
            if (v.checkPassword(newPassword) && !newPassword.equals(confirmPassword)) {
                request.setAttribute("errorconfirmnewpass", "Confirm password is not the same with new password");
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
            if (v.checkPassword(newPassword) && newPassword.equals(confirmPassword)) {
                int id = user.getUser_id();
                String email = user.getEmail();
                String username = user.getUsername();
                String fullname = user.getFullname();
                String address = user.getAddress();
                int role = user.getRole();
                Date creatat = user.getCreate_at();
                String avatar = user.getAvatar();
                String phone = user.getPhone();
                UserDao dao = new UserDao();
                User userUpdate = new User(id, email, newPassword, username, fullname, address, avatar, role, creatat, phone);
                dao.updateUser(userUpdate);
                session.removeAttribute("account");
                session.setAttribute("account", userUpdate);
                response.sendRedirect("Home.jsp");
            }
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
