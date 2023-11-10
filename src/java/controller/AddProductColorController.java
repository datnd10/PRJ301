/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDao;
import dao.ProductColorDao;
import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Category;
import model.Product;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "AddProductColorController", urlPatterns = {"/add-product-color"})
public class AddProductColorController extends HttpServlet {

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
            out.println("<title>Servlet AddProductColor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductColor at " + request.getContextPath() + "</h1>");
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
        ProductDao dao = new ProductDao();
        List<Product> list = dao.getAll();
        request.setAttribute("listProduct", list);
        request.getRequestDispatcher("AddProductColor.jsp").forward(request, response);
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
        String product_id = request.getParameter("productid");
        String color = request.getParameter("color");
        String quantity_raw = request.getParameter("quantity");
        String price_raw = request.getParameter("price");
        String description = request.getParameter("description");
        String creatat_raw = request.getParameter("creatat");
        String image = request.getParameter("image");
        try {
            int quantity = Integer.parseInt(quantity_raw);
            float price = Float.parseFloat(price_raw);
            int productID = Integer.parseInt(product_id);
            Date creatat = Date.valueOf(creatat_raw);
            ProductDao proddao = new ProductDao();
            ProductColorDao dao = new ProductColorDao();
            if (quantity < 0 || price < 0) {
                List<Product> list = proddao.getAll();
                request.setAttribute("listProduct", list);
                request.setAttribute("error", "Must be >0");
                request.getRequestDispatcher("AddProductColor.jsp").forward(request, response);
            } else {
                dao.addProductColor(color, quantity, price, description, image, productID, creatat, 0);
                response.sendRedirect("list-product-color");
            }
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
