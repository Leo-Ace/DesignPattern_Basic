package dao;

import configs.ConnectDB;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements GeneralDAO<Product>{
    private List<Product> data;
    private static ProductDAOImp instance;

    private ProductDAOImp() {
        this.data = new ArrayList<>();
    }

    public static ProductDAOImp getInstance() {
        if (instance == null){
            instance = new ProductDAOImp();
        }
        return instance;
    }

    @Override
    public List<Product> get() {
        List<Product> listData = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Select pr.*, ct.name as 'categoryName' from products pr join categories ct on pr.categoryId = ct.id");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("categoryId"),
                        rs.getBoolean("status"),
                        rs.getString("categoryName")
                );
                listData.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return listData;
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> listData = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Select pr.*, ct.name as 'categoryName' from products pr join categories ct on pr.categoryId = ct.id where pr.name like '%?%'");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("categoryId"),
                        rs.getBoolean("status"),
                        rs.getString("categoryName")
                );
                listData.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return listData;
    }

    @Override
    public Product findId(Object id) {
        Product product = null;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Select pr.*, ct.name as 'categoryName' from products pr join categories ct on pr.categoryId = ct.id where pr.id = ?");
            pst.setString(1, (String) id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("categoryId"),
                        rs.getBoolean("status"),
                        rs.getString("categoryName")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return product;
    }

    @Override
    public boolean add(Product entity) {
        boolean result = false;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("insert into products values(?,?,?,?,?)");
            pst.setString(1, entity.getId());
            pst.setString(2, entity.getName());
            pst.setDouble(3, entity.getPrice());
            pst.setInt(4, entity.getCategoryId());
            pst.setBoolean(5, entity.isStatus());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean edit(Product entity) {
        boolean result = false;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("update products set name = ?, price = ?, categoryId = ?, status = ? where id =?");
            pst.setString(5, entity.getId());
            pst.setString(1, entity.getName());
            pst.setDouble(2, entity.getPrice());
            pst.setInt(3, entity.getCategoryId());
            pst.setBoolean(4, entity.isStatus());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean remove(Product entity) {
        boolean result = false;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("delete from products where id =?");
            pst.setString(1, entity.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return result;
    }
}
