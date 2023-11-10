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
import model.Product;

/**
 *
 * @author Dac Dat
 */
public class ProductDao extends DBContext {

    public void addProduct(String name, String description, int cateId, Date creatat, int star) {
        String sql = "INSERT INTO [dbo].[product]\n"
                + "           ([name]\n"
                + "           ,[description]\n"
                + "           ,[category_id]\n"
                + "           ,[create_at],[star])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, cateId);
            ps.setDate(4, creatat);
            ps.setInt(5, star);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public Product checkName(String name) {
        String sql = "Select * from [product] where name = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Product category = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(6));
                return category;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int getTotalProduct(String search) {
        String sql = "Select count(*) from [product] where 1 = 1";
        if (search != null) {
            sql += " and name like '%" + search + "%'";
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

    public void deleteProductById(int id) {
        String sql = "Delete from [product] where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product getProductByID(int id) {
        String sql = "Select * from [product] where product_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Product category = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(6));
                return category;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE [dbo].[product]\n"
                + "   SET [name] = ? \n"
                + "      ,[description] = ? \n"
                + "      ,[category_id] = ? \n"
                + "      ,[create_at] = ? \n"
                + "      ,[star] = ? \n"
                + " WHERE product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getCategory_id());
            ps.setDate(4, product.getCreat_at());
            ps.setInt(5, product.getStar());
            ps.setInt(6, product.getProduct_id());
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public void updateStar(int star, int product_id) {
        String sql = "UPDATE [dbo].[product]\n"
                + "   SET [star] = ? \n"
                + " WHERE product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, star);
            ps.setInt(2, product_id);
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public List<Product> getAll() {
        String sql = "select * from [product]";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(6));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> paging(int page, String search) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from [product] where 1 = 1 ";
        if (search != null) {
            sql += " and name like '%" + search + "%'" + " order by product_id asc offset ? rows fetch next 6 rows only";

        } else {
            sql += " order by product_id asc offset ? rows fetch next 6 rows only";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(4));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalProductHome(String search, Integer category, Float priceFrom, Float priceTo, Integer star) {
        String sql = "	SELECT COUNT(*) AS total_rows\n"
                + "FROM (\n"
                + "    SELECT\n"
                + "        product.product_id,\n"
                + "        product.name,\n"
                + "        product.category_id,\n"
                + "        product.create_at,\n"
                + "        star,\n"
                + "        SUM(product_color.sold_quantity) AS total_sold,\n"
                + "        AVG(product_color.price) AS price\n"
                + "    FROM product\n"
                + "    JOIN product_color ON product_color.product_id = product.product_id where 1 = 1";
        if (search != null) {
            if (!search.isEmpty()) {
                sql += " and product.name like '%" + search + "%'";
            }
        }
        if (category != null) {
            sql += " and product.category_id = " + category;
        }
        if (priceFrom != null) {
            sql += " and price >= " + priceFrom;
        }
        if (priceTo != null) {
            sql += " and price <= " + priceTo;
        }
        if (star != null) {
            sql += " and star = " + star;
        }
        sql += " GROUP BY product.product_id, product.name, product.category_id, product.create_at, star) AS subquery;";
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

    public List<Product> pagingHome(int page, String search, String sort, Integer category, Float priceFrom, Float priceTo, Integer star) {
        List<Product> list = new ArrayList<>();
        String sql = "	SELECT product.product_id,product.name, product.description, product.category_id, product.create_at, star, SUM(product_color.sold_quantity) As total_sold,\n"
                + "	AVG(product_color.price) as price \n"
                + "	from product join product_color on product_color.product_id = product.product_id where 1 = 1 ";
        if (search != null) {
            if (!search.isEmpty()) {
                sql += " and product.name like '%" + search + "%'";
            }
        }
        if (category != null) {
            sql += " and product.category_id = " + category;
        }
        if (priceFrom != null) {
            sql += " and price >= " + priceFrom;
        }
        if (priceTo != null) {
            sql += " and price <= " + priceTo;
        }
        if (star != null) {
            sql += " and star = " + star;
        }
        sql += " group by product.product_id,product.name, product.description, product.category_id, product.create_at, star ";
        if (sort != null) {
            if (!sort.isEmpty()) {
                sql += " order by " + sort;
            } else {
                sql += " order by product.product_id ";
            }
        }
        if (sort == null) {
            sql += " order by product.product_id ";
        }
        sql += " offset ? rows fetch next 6 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(6));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        System.out.println(dao.getTotalProductHome(null, null, null, null, null));
    }
}
