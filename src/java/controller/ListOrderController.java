/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDao;
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
import java.util.List;
import model.Order;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "ListOrderController", urlPatterns = {"/list-order"})
@MultipartConfig(maxFileSize = 16177215)
public class ListOrderController extends HttpServlet {

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
            out.println("<title>Servlet ListOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListOrderController at " + request.getContextPath() + "</h1>");
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
        OrderDao dao = new OrderDao();
        String priceFrom_raw = request.getParameter("priceFrom");
        String priceTo_raw = request.getParameter("priceTo");
        String dateFrom_raw = request.getParameter("dateFrom");
        String dateTo_raw = request.getParameter("dateTo");
        Double priceFrom = ((priceFrom_raw == null || priceFrom_raw.equals("")) ? null : Double.parseDouble(priceFrom_raw));
        Double priceTo = ((priceTo_raw == null || priceTo_raw.equals("")) ? null : Double.parseDouble(priceTo_raw));
        Date dateFrom = ((dateFrom_raw == null || dateFrom_raw.equals("")) ? null : Date.valueOf(dateFrom_raw));
        Date dateTo = ((dateTo_raw == null || dateTo_raw.equals("")) ? null : Date.valueOf(dateTo_raw));
        int totalPage = dao.getTotalOrder(priceFrom, priceTo, dateFrom, dateTo);
        int endPage = totalPage / 5;
        if (totalPage % 5 != 0) {
            endPage++;
        }
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        List<Order> list = dao.pagingAll(page, priceFrom, priceTo, dateFrom, dateTo);
        if (priceFrom != null) {
            request.setAttribute("priceFrom", priceFrom);
        }
        if (priceTo != null) {
            request.setAttribute("priceTo", priceTo);
        }
        if (dateFrom != null) {
            request.setAttribute("dateFrom", dateFrom);
        }
        if (dateTo != null) {
            request.setAttribute("dateTo", dateTo);
        }
        request.setAttribute("page", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("list", list);
        request.getRequestDispatcher("ListOrder.jsp").forward(request, response);
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
