package manager;

import dao.GeneralDAO;
import entity.Category;
import factory.DAOFactory;

import java.util.List;
import java.util.Scanner;

public class CategoryManager {
    public void menu() {
        System.out.println("____________Quản lý danh mục_____________");
        System.out.println("1. Hiển thị d/s danh mục sắp xếp theo tên A-Z (status hiển thị ẩn/hiện)");
        System.out.println("2. Thêm mới danh mục");
        System.out.println("3. Cập nhật danh mục");
        System.out.println("4. Xóa danh mục");
        System.out.println("5. Quay lai");
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        DAOFactory daoFactory = DAOFactory.getInstance();
        GeneralDAO<Category> categoryGeneralDAO = daoFactory.getDao(Category.class);
        int choose;
        do {
            menu();
            System.out.print("Chon: ");
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1 -> {
                    List<Category> data = categoryGeneralDAO.get();
                    System.out.println("D/s danh mục sắp xếp theo tên A-Z");
                    System.out.printf("%10s %20s %10s\n", "Id", "Name", "Status");
                    for (Category category: data) {
                        category.display();
                    }
                    break;
                }
                case 2 -> {
                    System.out.println("Thêm mới danh mục");
                    Category category = new Category();
                    category.input();
                    Category check = categoryGeneralDAO.findId(category.getId());
                    if(check != null) {
                        System.out.println("Id đã tồn tại!");
                    } else {
                        boolean result = categoryGeneralDAO.add(category);
                        if(result) System.out.println("Thêm mới thành công!");
                        else System.out.println("Thêm mới thất bại!");
                    }
                    break;
                }
                case 3 -> {
                    System.out.println("Cập nhật danh mục");
                    System.out.print("Nhập mã danh mục cần cập nhật: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Category cateUpdate = categoryGeneralDAO.findId(id);
                    if (cateUpdate == null){
                        System.out.println("Mã danh mục ko tồn tại!");
                    } else {
                        cateUpdate.inputUpdate();
                        boolean result = categoryGeneralDAO.edit(cateUpdate);
                        if(result) System.out.println("Cập nhật thành công!");
                        else System.out.println("Cập nhật thất bại");
                    }
                    break;
                }
                case 4 -> {
                    System.out.println("Xóa danh mục");
                    System.out.print("Nhập vào mã danh mục cần xóa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Category category = categoryGeneralDAO.findId(id);
                    if (category == null){
                        System.out.println("Không tìm thấy danh mục có id "+id);
                    } else {
                        boolean result = categoryGeneralDAO.remove(category);
                        if(result) System.out.println("Xóa thành công!");
                        else System.out.println("Xóa thất bại");
                    }
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
