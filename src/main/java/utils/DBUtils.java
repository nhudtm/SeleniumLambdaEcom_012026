package utils;

import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/lambda2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCategoryProductQuantityFromDBByCategoryName(String categoryName) {
        String sql = "select  count(*) as quantity from product p join category c on p.category_id = c.category_id where c.name = ?";
        Map<String, Integer> categoryMap = new HashMap<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    public static Map<String, Integer> getCategoryProductQuantityModelFromDB() {
        String sql = "select c.name as category_name, count(*) as quantity from product p join category c on p.category_id = c.category_id group by c.name";
        Map<String, Integer> categoryMap = new HashMap<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryMap.put(rs.getString("category_name"), rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryMap; // always return a map, never null
    }

    public static List<Product> getAllProductsFromDB() {
        String sql = "select p.product_id, p.name, p.price, p.description, p.is_active from product p ";
        List<Product> productList = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setActive(rs.getBoolean("is_active"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public static int getProductMediaNumber(int productId) {
        String sql ="SELECT p.product_id, name, count(*) as numberOfMedia FROM product p join `product_media` pm on p.product_id = pm.product_id  where p.product_id = ? group by p.product_id";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("numberOfMedia");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static List<String> getProductMediaSrc(int productId) {
        String sql = "SELECT p.product_id, name, pm.media_url FROM product p join product_media pm on p.product_id = pm.product_id where p.product_id=?";
        List<String> mediaSrcList = new java.util.ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mediaSrcList.add(rs.getString("media_url"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mediaSrcList;
    }

    public static String getMainImageSrc(int productId) {
        String sql = "SELECT p.product_id, name, pm.media_url FROM product p join product_media pm on p.product_id = pm.product_id where p.product_id=? and pm.isMain = 'true'";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("media_url");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Product getProductInfoById(int productId) {
        String sql = "select p.product_id, p.name, p.price, p.description, p.is_active from product p where p.product_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setActive(rs.getBoolean("is_active"));
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String getProductVideoHref(int productId) {
        String sql = "SELECT p.product_id, name, pm.media_url FROM product p join product_media pm on p.product_id = pm.product_id where p.product_id=? and pm.media_type = 'video'";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("media_url");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<String> getAllCategories() {
        String sql = "select name from category";
        List<String> categoryList = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryList.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
}
