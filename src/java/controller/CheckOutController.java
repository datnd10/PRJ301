/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dao.CartDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductColorDao;
import dao.ProductDao;
import dao.ShippingDao;
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
import model.Cart;
import model.OrderDetail;
import model.Product;
import model.ProductColor;
import model.Shipping;
import model.User;

/**
 *
 * @author Dac Dat
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/check-out"})
@MultipartConfig(maxFileSize = 16177215)
public class CheckOutController extends HttpServlet {

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
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
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
        CartDao cartDao = new CartDao();
        ProductColorDao prodColorDao = new ProductColorDao();
        ProductDao prodDao = new ProductDao();
        ShippingDao shippingDao = new ShippingDao();
        List<Cart> listCart = cartDao.getCartWithUserID(user.getUser_id());
        List<Product> listProduct = prodDao.getAll();
        List<ProductColor> listProductColor = prodColorDao.getAll();
        List<Shipping> listShipping = shippingDao.getAllShipping();
        String productsJSON = new Gson().toJson(listCart);
        request.setAttribute("productsJSON", productsJSON);
        request.setAttribute("listCart", listCart);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listProductColor", listProductColor);
        request.setAttribute("listShipping", listShipping);
        request.getRequestDispatcher("CheckOut.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        String fullname = request.getParameter("name");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String address = request.getParameter("address");
        String message = request.getParameter("message");
        String total = request.getParameter("total");
        String shipping = request.getParameter("shipping");
        OrderDao orderdao = new OrderDao();
        String creatat_raw = request.getParameter("creatat");
        CartDao cartdao = new CartDao();
        ProductColorDao proddao = new ProductColorDao();
        OrderDetailDao orderdetaildao = new OrderDetailDao();
        if (fullname == null || phone == null || city == null || district == null || ward == null || address == null) {
            response.getWriter().print("Invalid input");
            return;
        }
        if (fullname.isEmpty() || phone.isEmpty() || city.isEmpty() || district.isEmpty() || ward.isEmpty() || address.isEmpty()) {
            response.getWriter().print("Invalid input");
            return;
        }
        Date creatat = Date.valueOf(creatat_raw);
        String fullAddress = address + "," + ward + "," + district + "," + city;

        
        orderdao.addToOrder(user.getUser_id(), Float.parseFloat(total), fullname, fullAddress, phone, message, creatat,1,Integer.parseInt(shipping));
        int orderId = orderdao.getOrderId(user.getUser_id());
        System.out.println(orderId);
        List<Cart> listcart = cartdao.getCartWithUserID(user.getUser_id());
        System.out.println(listcart);
        for (Cart cart : listcart) {
            ProductColor pc = proddao.getProductColorByID(cart.getProduct_color_id());
            proddao.updateQuantity((pc.getQuantity() - cart.getQuantity()), pc.getSold_quantity() + cart.getQuantity(), cart.getProduct_color_id());
            orderdetaildao.addToOrderDetail(orderId, cart.getProduct_color_id(), cart.getQuantity());
        }
        cartdao.deleteCart(user.getUser_id());
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
