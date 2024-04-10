package dao;

import configs.ConnectDB;
import entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImp implements GeneralDAO<Category>{
    private List<Category> data;
    private static CategoryDAOImp instance;

    private CategoryDAOImp() {
        this.data = new ArrayList<Category>();
    }

    public static CategoryDAOImp getInstance() {
        if (instance == null){
            instance = new CategoryDAOImp();
        }
        return instance;
    }

    @Override
    public List<Category> get() {
        List<Category> listData = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from categories order by name");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status")
                );
                listData.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return listData;
    }

    @Override
    public List<Category> getByName(String name) {
        List<Category> listData = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from categories where name like '%?%'");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status")
                );
                listData.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return listData;
    }

    @Override
    public Category findId(Object id) {
        Category category = null;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from categories where id = ?");
            pst.setInt(1, (Integer) id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return category;
    }

    @Override
    public boolean add(Category entity) {
        boolean result = false;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("insert into categories values(?,?,?)");
            pst.setInt(1, entity.getId());
            pst.setString(2, entity.getName());
            pst.setBoolean(3, entity.isStatus());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean edit(Category entity) {
        boolean result = false;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("update categories set name = ?, status = ? where id = ?");
            pst.setInt(3, entity.getId());
            pst.setString(1, entity.getName());
            pst.setBoolean(2, entity.isStatus());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean remove(Category entity) {
        boolean result = false;
        Connection conn = ConnectDB.openConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("delete from categories where id = ?");
            pst.setInt(1, entity.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return result;
    }
}
