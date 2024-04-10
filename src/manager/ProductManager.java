package manager;

import dao.GeneralDAO;
import entity.Category;
import entity.Product;
import factory.DAOFactory;

import java.util.List;
import java.util.Scanner;

public class ProductManager {
    public void menu() {
        System.out.println("____________Quản lý sản phẩm_____________");
        System.out.println("1. Hiển thị d/s sản phẩm (id, name, price, categoryName, status(ẩn/hiện))");
        System.out.println("2. Thêm mới sản phẩm");
        System.out.println("3. Cập nhật thông tin sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Quay lai");
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        DAOFactory daoFactory = DAOFactory.getInstance();
        GeneralDAO<Product> productGeneralDAO = daoFactory.getDao(Product.class);
        GeneralDAO<Category> categoryGeneralDAO = daoFactory.getDao(Category.class);
        int choose;
        do {
            menu();
            System.out.print("Chon: ");
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1 -> {
                    List<Product> data = productGeneralDAO.get();
                    System.out.println("D/s sản phẩm (id, name, price, categoryName, status(ẩn/hiện))");
                    System.out.printf("%10s %20s %10s %20s %10s\n", "Id", "Name", "Price", "CategoryName", "Status");
                    for (Product product: data) {
                        product.display();
                    }
                    break;
                }
                case 2 -> {
                    System.out.println("Thêm mới sản phẩm");
                    Product product = new Product();
                    product.input();
                    Product check = productGeneralDAO.findId(product.getId());
                    if(check != null) {
                        System.out.println("Id đã tồn tại!");
                    } else {
                        if(categoryGeneralDAO.findId(product.getCategoryId()) == null) {
                            System.out.println("Mã danh mục không tồn tại!");
                        } else {
                            boolean result = productGeneralDAO.add(product);
                            if(result) System.out.println("Thêm mới thành công!");
                            else System.out.println("Thêm mới thất bại!");
                        }
                    }
                    break;
                }
                case 3 -> {
                    System.out.println("Cập nhật sản phẩm");
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String id = sc.nextLine();
                    Product prdUpdate = productGeneralDAO.findId(id);
                    if (prdUpdate == null){
                        System.out.println("Mã sản phẩm ko tồn tại!");
                    } else {
                        prdUpdate.inputUpdate();
                        if(categoryGeneralDAO.findId(prdUpdate.getCategoryId()) == null) {
                            System.out.println("Mã danh mục không tồn tại!");
                        } else {
                            boolean result = productGeneralDAO.edit(prdUpdate);
                            if(result) System.out.println("Cập nhật thành công!");
                            else System.out.println("Cập nhật thất bại");
                        }
                    }
                    break;
                }
                case 4 -> {
                    System.out.println("Xóa sản phẩm");
                    System.out.print("Nhập vào mã sản phẩm cần xóa: ");
                    String id = sc.nextLine();
                    Product product = productGeneralDAO.findId(id);
                    if (product == null){
                        System.out.println("Không tìm thấy sản phẩm có id "+id);
                    }
                    boolean result = productGeneralDAO.remove(product);
                    if(result) System.out.println("Xóa thành công!");
                    else System.out.println("Xóa thất bại");
                    break;
                }
                case 5 -> {
                    System.out.println("Quay lại!");
                }
                default -> {
                    System.out.println("Lựa chọn không phù hợp!");
                    break;
                }
            }
        } while (choose != 5);
    }
}
