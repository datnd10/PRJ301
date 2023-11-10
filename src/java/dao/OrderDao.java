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
import model.Order;
import model.User;
import model.json.WSToast;

/**
 *
 * @author Dac Dat
 */
public class OrderDao extends DBContext {

    public void addToOrder(int user_id, float total_money, String name, String address, String phone, String message, Date creat_at, int status_id, int shipping_id) {
        String sql = "INSERT INTO [dbo].[order]\n"
                + "           ([user_id]\n"
                + "           ,[total_money]\n"
                + "           ,[name]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[message]\n"
                + "           ,[create_at]\n"
                + "           ,[status_id]\n"
                + "           ,[shipping_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setFloat(2, total_money);
            ps.setString(3, name);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setString(6, message);
            ps.setDate(7, creat_at);
            ps.setInt(8, status_id);
            ps.setInt(9, shipping_id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public int getOrderId(int user_id) {
        String sql = "SELECT order_id\n"
                + "FROM [dbo].[order]\n"
                + "WHERE user_id = ?";
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, user_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return (list.get(list.size() - 1));
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalOrderByUserId(int user_id) {
        String sql = "Select count(*) from [order] WHERE user_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, user_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getTotalOrder(Double priceFrom, Double priceTo, Date dateFrom, Date dateTo) {
        String sql = "Select count(*) from [order] where 1= 1";
        if (priceFrom != null) {
            sql += "and total_money >= " + priceFrom;
        }
        if (priceTo != null) {
            sql += "and total_money <= " + priceTo;
        }
        if (dateFrom != null) {
            sql += "and create_at >= '" + dateFrom + "' ";
        }
        if (dateTo != null) {
            sql += "and create_at <= '" + dateTo + "' ";
        }
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Order> paging(int user_id, int page) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from [order] where user_id = ? order by order_id desc offset ? rows fetch next 5 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, (page - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9), rs.getInt(10));
                list.add(order);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Order> pagingAll(int page, Double priceFrom, Double priceTo, Date dateFrom, Date dateTo) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from [order] where 1= 1";
        if (priceFrom != null) {
            sql += "and total_money >= " + priceFrom;
        }
        if (priceTo != null) {
            sql += "and total_money <= " + priceTo;
        }
        if (dateFrom != null) {
            sql += "and create_at >= '" + dateFrom + "' ";
        }
        if (dateTo != null) {
            sql += "and create_at <= '" + dateTo + "' ";
        }
        sql += " order by order_id desc offset ? rows fetch next 5 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9), rs.getInt(10));
                list.add(order);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteOrderById(int user_id, int order_id) {
        String sql = "Delete from [order] where user_id=? and order_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, order_id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Order getOrder(int order_id) {
        String sql = "Select * from [order] WHERE order_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, order_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9), rs.getInt(10));
                return order;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int checkDate(int order_id) {
        String sql = "	SELECT DATEDIFF(day, create_at, GETDATE()) AS day_difference\n"
                + "	FROM [order]\n"
                + "	WHERE order_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, order_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public void updateStatus(int order_id, int status_id) {
        String sql = "UPDATE [dbo].[order]\n"
                + "   SET [status_id] = ?\n"
                + " WHERE order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status_id);
            ps.setInt(2, order_id);
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public int getTotalOrderWithCondition(Integer status_id) {
        String sql = "Select count(*) from [order]";
        if (status_id != null) {
            sql += " where status_id = " + status_id;
        }
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public float getIncomes() {
        String sql = "select SUM(total_money) from [order] where status_id = 4";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public float getIncomes(Date date) {
        String sql = "select SUM(total_money) from [order] where status_id = 4 where create_at = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Order> getAllWeek(Date from, Date to) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from [order] where  status_id = 4 and  create_at >= ? and create_at <= ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, from);
            ps.setDate(2, to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9), rs.getInt(10));
                list.add(order);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public WSToast getOrderToastInfo(int userID)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDao userDAO = new UserDao();
        User user = userDAO.getUserByID(userID);
        try {
            String sql = "select [order].total_money, SUM(order_detail.quantity) as total_quantity \n"
                    + "	from [order] join order_detail on order_detail.order_id = [order].order_id\n"
                    + "	where  [order].order_id = (select top 1 [order].order_id from [order] order by order_id desc ) \n"
                    + "	group by [order].total_money";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println(sql);
            System.out.println(userID);
            while (rs.next()) {
                int totalPrice = rs.getInt("total_money");
                int totalQuantity = rs.getInt("total_quantity");
                System.out.println(totalPrice);
                System.out.println(totalQuantity);
                WSToast t = new WSToast(user.getUsername(), totalQuantity, totalPrice);
                return new WSToast(user.getUsername(), totalQuantity, totalPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
