package factory;

import dao.CategoryDAOImp;
import dao.GeneralDAO;
import dao.ProductDAOImp;
import entity.Category;
import entity.Product;

public class DAOFactory {
    private static DAOFactory instance;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public <T> GeneralDAO<T> getDao(Class<T> tClass) {
        if(tClass.equals(Category.class)) {
            return (GeneralDAO<T>) CategoryDAOImp.getInstance();
        } else if(tClass.equals(Product.class)) {
            return (GeneralDAO<T>) ProductDAOImp.getInstance();
        }
        return null;
    }
}
