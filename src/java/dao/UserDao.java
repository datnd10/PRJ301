/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Dac Dat
 */
public class UserDao extends DBContext {

    public List<User> getAllUser() {
        String sql = "select * from [user]";
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                list.add(user);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteUserById(int id) {
        String sql = "Delete from [user] where user_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public User getUserByID(int id) {
        String sql = "Select * from [user] where user_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateUser(User user) {
        String sql = "UPDATE [dbo].[user]\n"
                + "   SET [email] = ? \n"
                + "      ,[password] = ? \n"
                + "      ,[username] = ? \n"
                + "      ,[fullname] = ? \n"
                + "      ,[address] = ? \n"
                + "      ,[avatar] = ? \n"
                + "      ,[role] = ? \n"
                + "      ,[create_at] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getFullname());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getAvatar());
            ps.setInt(7, user.getRole());
            ps.setDate(8, user.getCreate_at());
            ps.setString(9, user.getPhone());
            ps.setInt(10, user.getUser_id());
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public User checkUpdateEmail(String email, int user_id) {
        String sql = "Select * from [user] where email = ? and user_id != ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            pr.setInt(2, user_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User checkUpdatePhone(String phone, int user_id) {
        String sql = "Select * from [user] where phone = ? and user_id != ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, phone);
            pr.setInt(2, user_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User checkUpdateUsername(String username, int user_id) {
        String sql = "Select * from [user] where username = ? and user_id != ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, username);
            pr.setInt(2, user_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User checkAddEmail(String email) {
        String sql = "Select * from [user] where email = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User checkAddPhone(String phone) {
        String sql = "Select * from [user] where phone = ? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, phone);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public User checkAddUsername(String username) {
        String sql = "Select * from [user] where username = ? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, username);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return user;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void addUser(String email, String password, String username, String fullname, String address, String avatar, int role, Date creatat, String phone) {
        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([email]\n"
                + "           ,[password]\n"
                + "           ,[username]\n"
                + "           ,[fullname]\n"
                + "           ,[address]\n"
                + "           ,[avatar]\n"
                + "           ,[role]\n"
                + "           ,[create_at]\n"
                + "           ,[phone])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, username);
            ps.setString(4, fullname);
            ps.setString(5, address);
            ps.setString(6, avatar);
            ps.setInt(7, role);
            ps.setDate(8, creatat);
            ps.setString(9, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public User checkAccount(String username, String password) {
        String sql = "Select * from [user] where email = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User a = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalUser(String search) {
        String sql = "Select count(*) from [user] where 1 = 1";
        if (search != null) {
            sql += " and username like '%" + search + "%'";
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

    public List<User> paging(int page, String search) {
        List<User> list = new ArrayList<>();
        String sql = "select * from [user] where 1 = 1";
        if (search != null) {
            sql += " and username like '%" + search + "%'" + " order by USER_ID asc offset ? rows fetch next 7 rows only";

        } else {
            sql += " order by USER_ID asc offset ? rows fetch next 7 rows only";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 7);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getString("fullname"), rs.getString("address"), rs.getString("avatar"), rs.getInt("role"), rs.getDate("create_at"), rs.getString("phone"));
                list.add(user);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // Calculate the date for the previous Monday
        LocalDate monday = today.with(DayOfWeek.MONDAY);

        // Calculate the date for the following Sunday
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);
        System.out.println(monday);
        System.out.println(sunday);
        // Loop through the dates from Monday to Sunday
    }
}
