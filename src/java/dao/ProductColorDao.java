
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
import model.ProductColor;

/**
 *
 * @author Dac Dat
 */
public class ProductColorDao extends DBContext {

    public void deleteProductColorById(int id) {
        String sql = "Delete from [product_color] where product_color_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<ProductColor> getAll() {
        String sql = "select * from [product_color]";
        List<ProductColor> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductColor product = new ProductColor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getInt(9));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalProductColor(String search) {
        String sql = "Select count(*) from [product_color] left join product on product_color.product_id = product.product_id where 1 = 1 ";
        if(search!=null) {
            sql += " and product.name like '%" + search + "%'";
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

    public int getTotalSoldQuantity(int id) {
        String sql = "Select Sum(sold_quantity) from product_color where product_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public void addProductColor(String color, int quantity, float price, String description, String image, int productid, Date creatat, int soldquantty) {
        String sql = "INSERT INTO [dbo].[product_color]\n"
                + "           ([color]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[image]\n"
                + "           ,[product_id]\n"
                + "           ,[create_at]\n"
                + "           ,[sold_quantity])"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, color);
            ps.setInt(2, quantity);
            ps.setFloat(3, price);
            ps.setString(4, description);
            ps.setString(5, image);
            ps.setInt(6, productid);
            ps.setDate(7, creatat);
            ps.setInt(8, soldquantty);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ProductColor getProductColorByID(int id) {
        String sql = "Select * from [product_color] where product_color_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                ProductColor product = new ProductColor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getInt(9));
                return product;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<ProductColor> getProductColorByProductID(int id) {
        String sql = "select * from [product_color] where product_id = ?";
        List<ProductColor> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductColor product = new ProductColor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getInt(9));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateProduct(ProductColor product) {
        String sql = "UPDATE [dbo].[product_color]\n"
                + "   SET [color] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[image] = ? \n"
                + "      ,[product_id] = ? \n"
                + "      ,[create_at] = ? \n"
                + "      ,[sold_quantity] = ? \n"
                + " WHERE product_color_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getColor());
            ps.setInt(2, product.getQuantity());
            ps.setFloat(3, product.getPrice());
            ps.setString(4, product.getDesciption());
            ps.setString(5, product.getImage());
            ps.setInt(6, product.getProduct_id());
            ps.setDate(7, product.getCreate_at());
            ps.setInt(8, product.getSold_quantity());
            ps.setInt(9, product.getProduct_color_id());
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public List<ProductColor> paging(int page, String search) {
        List<ProductColor> list = new ArrayList<>();
        String sql = "select [product_color_id]\n"
                + "      ,[color]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,product_color.[description]\n"
                + "      ,[image]\n"
                + "      ,product_color.[product_id]\n"
                + "      ,product_color.[create_at]\n"
                + "      ,[sold_quantity]	  \n"
                + "      , product.name from [product_color] left join product on product_color.product_id = product.product_id where 1 = 1 ";
        if (search != null) {
            sql += " and product.name like '%" + search + "%'" + " order by product.product_id asc offset ? rows fetch next 4 rows only";

        } else {
            sql += " order by product.product_id asc offset ? rows fetch next 4 rows only";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductColor product = new ProductColor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getInt(9));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ProductColor getProductColorByColorAndProductID(int id, String color) {
        String sql = "Select * from [product_color] where product_id = ? AND color = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            pr.setString(2, color);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                ProductColor product = new ProductColor(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDate(8), rs.getInt(9));
                return product;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateQuantity(int quantity, int sold, int product_color_id) {
        String sql = "UPDATE [dbo].[product_color]\n"
                + "   SET [quantity] = ?\n"
                + "      ,[sold_quantity] = ? \n"
                + " WHERE product_color_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, sold);
            ps.setInt(3, product_color_id);
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }
    

    
    public static void main(String[] args) {
        ProductColorDao dao = new ProductColorDao();
        System.out.println(dao.getTotalProductColor("i"));
    }
}
