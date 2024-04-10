import manager.CategoryManager;
import manager.ProductManager;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            System.out.println("1. Quan ly danh muc");
            System.out.println("2. Quan ly san pham");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");
            choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1 -> {
                    CategoryManager categoryManager = new CategoryManager();
                    categoryManager.start();
                    break;
                }
                case 2 -> {
                    ProductManager productManager = new ProductManager();
                    productManager.start();
                    break;
                }
                case 3 -> {
                    System.out.println("Thoát chương trình!");
                    break;
                }
                default -> {
                    System.out.println("Lựa chọn không phù hợp!");
                }
            }
        } while (choose != 3);
    }
}
