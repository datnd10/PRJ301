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
import model.Shipping;


/**
 *
 * @author Dac Dat
 */
public class ShippingDao extends DBContext {

    public List<Shipping> getAllShipping() {
        String sql = "select * from [shipping]";
        List<Shipping> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shipping shipping = new Shipping(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                list.add(shipping);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public Shipping getShippingByID(int id) {
        String sql = "Select * from [shipping] where shipping_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Shipping shipping = new Shipping(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                return shipping;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
