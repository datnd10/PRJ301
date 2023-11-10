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
import model.Review;

/**
 *
 * @author Dac Dat
 */
public class ReviewDao extends DBContext {

    public void addReview(String description, int user_id, int product_color_id, String image, int star, Date creat_at) {
        String sql = "INSERT INTO [dbo].[review]\n"
                + "           ([description]\n"
                + "           ,[user_id]\n"
                + "           ,[product_color_id]\n"
                + "           ,[image]\n"
                + "           ,[star]\n"
                + "           ,[create_at])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, description);
            ps.setInt(2, user_id);
            ps.setInt(3, product_color_id);
            ps.setString(4, image);
            ps.setInt(5, star);
            ps.setDate(6, creat_at);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Review> getAllWithProductColorID(int product_color_id) {
        String sql = "SELECT * FROM [dbo].[review] where [product_color_id] = ?";
        List<Review> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product_color_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7));
                list.add(review);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteReviewById(int review_id) {
        String sql = "Delete from [review] where review_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, review_id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Review getReviewByID(int user_id, int product_color_id) {
        String sql = "Select * from [review] where user_id = ? and product_color_id = ? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, user_id);
            pr.setInt(2, product_color_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Review review = new Review(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7));
                return review;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Review getReviewByReviewID(int review_id) {
        String sql = "Select * from [review] where review_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, review_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Review review = new Review(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7));
                return review;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateProduct(Review review) {
        String sql = "UPDATE [dbo].[review]\n"
                + "   SET [description] = ? \n"
                + "      ,[image] = ? \n"
                + "      ,[star] = ? \n"
                + "      ,[create_at] = ?\n"
                + " WHERE [user_id] = ? and [review_id] = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, review.getDescription());
            ps.setString(2, review.getImage());
            ps.setInt(3, review.getStar());
            ps.setDate(4, review.getCreate_at());
            ps.setInt(5, review.getUser_id());
            ps.setInt(6, review.getReview_id());
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }
    
    public int getTotalReview(int product_color_id) {
        String sql = "SELECT COUNT(*) FROM [dbo].[review] where [product_color_id] = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, product_color_id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }
    

    public static void main(String[] args) {
        ReviewDao dao = new ReviewDao();
        List<Review> list = dao.getAllWithProductColorID(4);
        for (Review r : list) {
            System.out.println(list);
        }
        System.out.println(dao.getTotalReview(4));
    }
}
