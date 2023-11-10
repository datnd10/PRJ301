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
import model.Category;

/**
 *
 * @author Dac Dat
 */
public class CategoryDao extends DBContext {

    public List<Category> getAll() {
        String sql = "select * from [category]";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getDate("create_at"));
                list.add(category);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteCategoryById(int id) {
        String sql = "Delete from [category] where category_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Category getCategoryByID(int id) {
        String sql = "Select * from [category] where category_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getDate("create_at"));
                return category;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateCategory(Category category) {
        String sql = "UPDATE [dbo].[category]\n"
                + "   SET [name] = ? \n"
                + "      ,[description] = ? \n"
                + "      ,[create_at] = ?\n"
                + " WHERE category_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setDate(3, category.getCreat_at());
            ps.setInt(4, category.getCategory_id());
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public void addCategory(String name, String description, Date creatat) {
        String sql = "INSERT INTO [dbo].[category]\n"
                + "           ([name]\n"
                + "           ,[description]\n"
                + "           ,[create_at])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDate(3, creatat);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public Category checkName(String name) {
        String sql = "Select * from [category] where name = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getDate("create_at"));
                return category;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getTotalUser(String search) {
        String sql = "Select count(*) from [category] where 1 = 1";
        if(search != null) {
            sql += " and name like '%" +search +"%'";   
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

    public List<Category> paging(int page, String search) {
        List<Category> list = new ArrayList<>();
        String sql = "select * from [category] where 1 = 1";
        if (search != null) {
            sql += " and name like '%" + search + "%'" + " order by category_id asc offset ? rows fetch next 7 rows only";

        } else {
            sql += " order by category_id asc offset ? rows fetch next 7 rows only";
        }
        try {
            System.out.println(page);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 7);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description"), rs.getDate("create_at"));
                list.add(category);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.getAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }

}
