/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author Dac Dat
 */
public class OrderDetailDao extends DBContext {

    public void addToOrderDetail(int order_id, int product_color_id, int quantity) {
        String sql = "INSERT INTO [dbo].[order_detail]\n"
                + "           ([order_id]\n"
                + "           ,[product_color_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            ps.setInt(2, product_color_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<OrderDetail> getAllWithOrderID(int order_id) {
        String sql = "SELECT * FROM [dbo].[order_detail] where [order_id] = ?";
        List<OrderDetail> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                list.add(orderDetail);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
        public void deleteOrderById(int order_id) {
        String sql = "Delete from [order_detail] where  order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, order_id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
