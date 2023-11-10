/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDetailDao;
import dao.ProductColorDao;
import dao.ProductDao;
import dao.ReviewDao;
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
import model.OrderDetail;
import model.Product;
import model.ProductColor;
import model.Review;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "AddReviewController", urlPatterns = {"/add-review"})
@MultipartConfig(maxFileSize = 16177215)
public class AddReviewController extends HttpServlet {

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
            out.println("<title>Servlet AddReviewController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddReviewController at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        ProductDao proddao = new ProductDao();
        ProductColorDao prodcolordao = new ProductColorDao();
        ProductColor productColor = prodcolordao.getProductColorByID(Integer.parseInt(id_raw));
        Product product = proddao.getProductByID(productColor.getProduct_id());
        request.setAttribute("productColorId", id_raw);
        request.setAttribute("productColor", productColor);
        request.setAttribute("product", product);
        request.getRequestDispatcher("AddReview.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        String productColorId = request.getParameter("productColorId");
        String rating = request.getParameter("rating");
        String description = request.getParameter("description");
        String creatat = request.getParameter("creatat");
        String image = request.getParameter("image");
        ReviewDao reviewDao = new ReviewDao();
        Review review = reviewDao.getReviewByID(user.getUser_id(), Integer.parseInt(productColorId));
        if (review != null) {
            response.getWriter().print("Invalid");
            return;
        }
        reviewDao.addReview(description, user.getUser_id(), Integer.parseInt(productColorId), image, Integer.parseInt(rating), Date.valueOf(creatat));
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
