package entity;

import java.util.Scanner;

public class Category implements IBusiness {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        while (true) {
            this.name = sc.nextLine();
            if(name.trim() != "") {
                break;
            }
            System.out.printf("Loi! Hay nhap lai: ");
        }
        System.out.print("Nhập trạng thái mới: ");
        while (true) {
            try {
                this.status = Boolean.parseBoolean(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.printf("Loi! Hay nhap lai: ");
            }
        }
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id: ");
        while (true) {
            try {
                this.id = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.printf("Loi! Hay nhap lai: ");
            }
        }
        System.out.print("Nhập name: ");
        while (true) {
            this.name = sc.nextLine();
            if(name.trim() != "") {
                break;
            }
            System.out.printf("Loi! Hay nhap lai: ");
        }
        System.out.print("Nhập status: ");
        while (true) {
            try {
                this.status = Boolean.parseBoolean(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.printf("Loi! Hay nhap lai: ");
            }
        }
    }

    @Override
    public void display() {
        System.out.printf("%10d %20s %10s\n", id, name, (status?"Hiện":"Ẩn"));
    }
}
