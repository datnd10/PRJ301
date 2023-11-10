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
import java.util.List;
import model.Category;
import model.Product;
import model.ProductColor;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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
        CategoryDao catedao = new CategoryDao();
        ProductDao proddao = new ProductDao();
        ProductColorDao prodcolordao = new ProductColorDao();
        List<Category> listCate = catedao.getAll();
        List<ProductColor> listProdColor = prodcolordao.getAll();
        String search = request.getParameter("search");
        if (search != null) {
            request.setAttribute("search", search);
        }
        String sort = request.getParameter("sort");
        if (sort != null) {
            request.setAttribute("sort", sort);
        }
        String category_raw = request.getParameter("category");
        if (category_raw != null) {
            request.setAttribute("category", category_raw);
        }
        String pricefrom_raw = request.getParameter("pricefrom");
        if (pricefrom_raw != null) {
            request.setAttribute("pricefrom", pricefrom_raw);
        }
        String priceto_raw = request.getParameter("priceto");
        if (priceto_raw != null) {
            request.setAttribute("priceto", priceto_raw);
        }
        String star_raw = request.getParameter("star");
        if (star_raw != null) {
            request.setAttribute("star", star_raw);
        }
        Integer category = ((category_raw == null || category_raw.equals("")) ? null : Integer.parseInt(category_raw));
        Float pricefrom = ((pricefrom_raw == null || pricefrom_raw.equals("")) ? null : Float.parseFloat(pricefrom_raw));
        Float priceto = ((priceto_raw == null || priceto_raw.equals("")) ? null : Float.parseFloat(priceto_raw));
        Integer star = ((star_raw == null || star_raw.equals("")) ? null : Integer.parseInt(star_raw));
        int totalPage = proddao.getTotalProductHome(search, category, pricefrom, priceto, star);
        int endPage = totalPage / 6;
        if (totalPage % 6 != 0) {
            endPage++;
        }
        String indexPage = request.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        List<Product> listProd = proddao.pagingHome(page, search, sort, category, pricefrom, priceto, star);
        request.setAttribute("listCate", listCate);
        request.setAttribute("listProd", listProd);
        request.setAttribute("listProdColor", listProdColor);
        request.setAttribute("page", indexPage);
        request.setAttribute("endPage", endPage);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
