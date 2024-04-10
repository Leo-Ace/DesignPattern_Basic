package entity;

import java.util.Scanner;

public class Product implements IBusiness {
    private String id;
    private String name;
    private double price;
    private int categoryId;
    private boolean status;
    private String categoryName;

    public Product() {
    }

    public Product(String id, String name, double price, int categoryId, boolean status, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.status = status;
        this.categoryName = categoryName;
    }

    public String getCateName() {
        return categoryName;
    }

    public void setCateName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputUpdate() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tên mới: ");
        while (true){
            name = sc.nextLine();
            if(name.trim() != "") break;
            System.out.print("Loi! Hay nhap lai: ");
        }

        System.out.print("Nhập giá mới: ");
        while (true) {
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price < 1000) {
                    System.out.println("Giá sản phẩm tối thiểu phải là 1000");
                    System.out.print("Nhập price: ");
                } else break;
            }catch (Exception e){
                System.out.print("Loi! Hay nhap lai: ");
            }
        }

        System.out.print("Nhập categoryId: ");
        while (true){
            try {
                categoryId = Integer.parseInt(sc.nextLine());
                break;
            }catch (Exception e){
                System.out.print("Loi! Hay nhap lai: ");
            }
        }

        System.out.print("Nhập status: ");
        while (true){
            try {
                status = Boolean.parseBoolean(sc.nextLine());
                break;
            }catch (Exception e){
                System.out.print("Loi! Hay nhap lai: ");
            }
        }
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id: ");
        while (true){
            id = sc.nextLine();
            if(id.trim() != "") break;
            System.out.print("Loi! Hay nhap lai: ");
        }

        System.out.print("Nhập name: ");
        while (true){
            name = sc.nextLine();
            if(name.trim() != "") break;
            System.out.print("Loi! Hay nhap lai: ");
        }

        System.out.print("Nhập price: ");
        while (true) {
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price < 1000) {
                    System.out.println("Giá sản phẩm tối thiểu phải là 1000");
                    System.out.print("Nhập price: ");
                } else break;
            }catch (Exception e){
                System.out.print("Loi! Hay nhap lai: ");
            }
        }

        System.out.print("Nhập categoryId: ");
        while (true){
            try {
                categoryId = Integer.parseInt(sc.nextLine());
                break;
            }catch (Exception e){
                System.out.print("Loi! Hay nhap lai: ");
            }
        }

        System.out.print("Nhập status: ");
        while (true){
            try {
                status = Boolean.parseBoolean(sc.nextLine());
                break;
            }catch (Exception e){
                System.out.print("Loi! Hay nhap lai: ");
            }
        }

    }

    @Override
    public void display() {
        System.out.printf("%10s %20s %10.2f %20s %10s\n", id, name, price, categoryName, (status?"Hiện":"Ẩn"));
    }
}
