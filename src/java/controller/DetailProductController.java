/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductColorDao;
import dao.ProductDao;
import dao.ReviewDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductColor;
import model.Review;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "DetailProductController", urlPatterns = {"/detail-product"})
public class DetailProductController extends HttpServlet {

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
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProductController at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        ProductDao proddao = new ProductDao();
        ProductColorDao prodcolordao = new ProductColorDao();
        try {
            int id = Integer.parseInt(id_raw);
            Product prod = proddao.getProductByID(id);
            List<ProductColor> listprodcolor = prodcolordao.getProductColorByProductID(prod.getProduct_id());
            int totalSold = prodcolordao.getTotalSoldQuantity(prod.getProduct_id());
            List<Review> allReview = new ArrayList<>();
            ReviewDao reviewDao = new ReviewDao();
            int totalRate = 0;
            int totalStar = 0;
            for (ProductColor product : listprodcolor) {
                List<Review> list = reviewDao.getAllWithProductColorID(product.getProduct_color_id());
                for (Review review : list) {
                    allReview.add(review);
                    totalRate++;
                    totalStar += review.getStar();
                }
            }
            List<User> listUser = new UserDao().getAllUser();
            List<Product> listProduct = new ProductDao().getAll();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            if (totalStar > 0 && totalRate > 0) {
                int avgRate = Math.round(totalStar / totalRate);
                proddao.updateStar(avgRate, id);
                prod.setStar(avgRate);
                request.setAttribute("totalRate", totalRate);
                request.setAttribute("totalStar", totalStar);
            }
            request.setAttribute("listReview", allReview);
            request.setAttribute("listUser", listUser);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("totalSold", totalSold);
            request.setAttribute("listProductColor", listprodcolor);
            request.setAttribute("product", prod);
            request.setAttribute("userId", user.getUser_id());
        } catch (Exception e) {
        }
        request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
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
