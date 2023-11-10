/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;

/**
 *
 * @author Dac Dat
 */
public class CartDao extends DBContext {

    public void addToCart(int user_id, int product_color_id, int quantity) {
        String sql = "INSERT INTO [dbo].[cart]\n"
                + "           ([user_id]\n"
                + "           ,[product_color_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, product_color_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Cart> getCartWithUserID(int user_id) {
        String sql = "select * from [cart] where user_id = ?";
        List<Cart> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(cart);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateCartQuantity(int user_id, int product_color_id, int quantity) {
        String sql = "UPDATE [dbo].[cart]\n"
                + "   SET [quantity] = ?\n"
                + " WHERE user_id = ? and product_color_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, user_id);
            ps.setInt(3, product_color_id);
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public void deleteItemById(int user_id, int product_color_id) {
        String sql = "Delete from [cart] WHERE user_id = ? and product_color_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, product_color_id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Cart checkItemCart(int user_id, int product_color_id) {
        String sql = "Select * from [cart] WHERE user_id = ? and product_color_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, user_id);
            pr.setInt(2, product_color_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                return cart;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void deleteCart(int user_id) {
        String sql = "Delete from [cart] WHERE user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
