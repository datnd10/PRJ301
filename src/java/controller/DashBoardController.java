/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Order;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "DashBoardController", urlPatterns = {"/dash-board"})
public class DashBoardController extends HttpServlet {

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
            out.println("<title>Servlet DashBoardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardController at " + request.getContextPath() + "</h1>");
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
        if (user.getRole() != 0 && user.getRole() != 1) {
            request.getRequestDispatcher("NoPermission.jsp").forward(request, response);
            return;
        }
        UserDao userdao = new UserDao();
        OrderDao orderdao = new OrderDao();
        int totalOrder = orderdao.getTotalOrderWithCondition(null);
        int totalUser = userdao.getTotalUser("");
        float income = orderdao.getIncomes();
        int cancelOrder = orderdao.getTotalOrderWithCondition(5);
        int pendingOrder = orderdao.getTotalOrderWithCondition(1);
        int approvedOrder = orderdao.getTotalOrderWithCondition(2);
        int shippingOrder = orderdao.getTotalOrderWithCondition(3);
        int receivedOrder = orderdao.getTotalOrderWithCondition(4);
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);
        LocalDate currentDate = monday;
        currentDate = currentDate.plusDays(1);
        LocalDate tuesday = currentDate;
        currentDate = currentDate.plusDays(1);
        LocalDate wednesday = currentDate;
        currentDate = currentDate.plusDays(1);
        LocalDate thursday = currentDate;
        currentDate = currentDate.plusDays(1);
        LocalDate friday = currentDate;
        currentDate = currentDate.plusDays(1);
        LocalDate saturday = currentDate;
        List<Order> list = orderdao.getAllWeek(Date.valueOf(monday), Date.valueOf(sunday));
        float totalMonday = 0;
        float totalTuesday = 0;
        float totalWednesday = 0;
        float totalThurday = 0;
        float totalFriday = 0;
        float totalSaturday = 0;
        float totalSunday = 0;
        for(Order order: list) {
            System.out.println(order.getCreate_at().equals(Date.valueOf(monday)));
            if(order.getCreate_at().equals(Date.valueOf(monday))) {
                totalMonday+= order.getTotal_money();
            }
            if(order.getCreate_at().equals(Date.valueOf(tuesday))) {
                totalTuesday+= order.getTotal_money();
            }
            if(order.getCreate_at().equals(Date.valueOf(wednesday))) {
                totalWednesday+= order.getTotal_money();
            }
            if(order.getCreate_at().equals(Date.valueOf(thursday))) {
                totalThurday+= order.getTotal_money();
            }
            if(order.getCreate_at().equals(Date.valueOf(friday))) {
                totalFriday+= order.getTotal_money();
            }
            if(order.getCreate_at().equals(Date.valueOf(saturday))) {
                totalSaturday+= order.getTotal_money();
            }
            if(order.getCreate_at().equals(Date.valueOf(sunday)) ) {
                totalSunday+= order.getTotal_money();
            }
        }
        request.setAttribute("totalMonday", totalMonday);
        request.setAttribute("totalTuesday", totalTuesday);
        request.setAttribute("totalWednesday", totalWednesday);
        request.setAttribute("totalThurday", totalThurday);
        request.setAttribute("totalFriday", totalFriday);
        request.setAttribute("totalSaturday", totalSaturday);
        request.setAttribute("totalSunday", totalSunday);
        request.setAttribute("totalUser", totalUser);
        request.setAttribute("totalOrder", totalOrder);
        request.setAttribute("income", income);
        request.setAttribute("cancelOrder", cancelOrder);
        request.setAttribute("pendingOrder", pendingOrder);
        request.setAttribute("approvedOrder", approvedOrder);
        request.setAttribute("shippingOrder", shippingOrder);
        request.setAttribute("receivedOrder", receivedOrder);
        request.getRequestDispatcher("DashBoard.jsp").forward(request, response);
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
        processRequest(request, response);
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
