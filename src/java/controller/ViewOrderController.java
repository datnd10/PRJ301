/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductColorDao;
import dao.ProductDao;
import dao.ShippingDao;
import dao.StatusDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductColor;
import model.Shipping;
import model.Status;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "ViewOrderController", urlPatterns = {"/view-order"})
public class ViewOrderController extends HttpServlet {

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
            out.println("<title>Servlet ViewOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewOrderController at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        OrderDetailDao orderDetailDao = new OrderDetailDao();
        ProductDao proddao = new ProductDao();
        ProductColorDao prodcolordao = new ProductColorDao();
        ShippingDao shippingDao = new ShippingDao();
        OrderDao orderDao = new OrderDao();
        StatusDao statusDao = new StatusDao();
        List<OrderDetail> listOrder = orderDetailDao.getAllWithOrderID(Integer.parseInt(id_raw));
        List<Product> listProd = proddao.getAll();
        List<Shipping> shipping = shippingDao.getAllShipping();
        List<ProductColor> listProdColor = prodcolordao.getAll();
        List<Status> listStatus = statusDao.getAllStatus();
        Order order = orderDao.getOrder(Integer.parseInt(id_raw));
        request.setAttribute("orderID", Integer.parseInt(id_raw));
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("listProd", listProd);
        request.setAttribute("listProdColor", listProdColor);
        request.setAttribute("listStatus", listStatus);
        request.setAttribute("shipping", shipping);
        request.setAttribute("order", order);
        request.getRequestDispatcher("AdminViewOrder.jsp").forward(request, response);
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
