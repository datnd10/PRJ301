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
import model.Status;

/**
 *
 * @author Dac Dat
 */
public class StatusDao extends DBContext{
    
    public List<Status> getAllStatus() {
        String sql = "select * from [status]";
        List<Status> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Status status = new Status(rs.getInt(1), rs.getString(2));
                list.add(status);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public Status getStatusByID(int id) {
        String sql = "Select * from [status] where status_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Status status = new Status(rs.getInt(1), rs.getString(2));
                return status;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
