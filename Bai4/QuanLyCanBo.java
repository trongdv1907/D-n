package Bai4;

import java.util.*;

public class QuanLyCanBo {

    private static List<CanBo> danhSachCanBo = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Nhap thong tin danh sach can bo");
            System.out.println("2. Xuat danh sach giang vien khoa X");
            System.out.println("3. Tong so luong");
            System.out.println("4. Sap xep can bo theo luong va ten");
            System.out.println("5. Thoat");

            System.out.print("Nhap lua chon cua ban: ");
            int luaChon = scanner.nextInt();
            scanner.nextLine(); // Loại bỏ ký tự '\n'

            switch (luaChon) {
                case 1:
                    nhapThongTinDanhSachCanBo(scanner);
                    break;
                case 2:
                    xuatDanhSachNhanVien(scanner);
                    break;
                case 3:
                    tinhTongLuong();
                    break;
                case 4:
                    sapXepCanBo();
                    break;
                case 5:
                    System.out.println("Hen gap lai!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private static void nhapThongTinDanhSachCanBo(Scanner scanner) {
        System.out.print("Nhap so luong can bo: ");
        int soLuongCanBo = scanner.nextInt();
        scanner.nextLine();
    
        for (int i = 0; i < soLuongCanBo; i++) {
            System.out.println("\n--- Nhap thong tin can bo thu " + (i + 1) + " ---");
            System.out.print("Ho ten: ");
            String hoTen = scanner.nextLine();
    
            System.out.print("Loai can bo (giang vien / nhan vien): ");
            String loaiCanBo = scanner.nextLine();
    
            if (loaiCanBo.equalsIgnoreCase("giang vien")) {
                nhapThongTinGiangVien(hoTen, scanner);
            } else if (loaiCanBo.equalsIgnoreCase("nhan vien")) {
                nhapThongTinNhanVien(hoTen, scanner);
            } else {
                System.out.println("Loai can bo khong hop le!");
            }
        }
    }
    
    private static void nhapThongTinGiangVien(String hoTen, Scanner scanner) {
        System.out.print("Khoa: ");
        String khoa = scanner.nextLine();
    
        System.out.print("Trinh do (cu nhan, thac si, tien si): ");
        String trinhDo = scanner.nextLine();
    
        System.out.print("So tiet day: ");
        int soTietDay = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("He so luong: ");
        float heSoLuong = scanner.nextFloat();
        scanner.nextLine();
    
        int phuCap = tinhPhuCapGiangVien(trinhDo);
        CanBo canBo = new CanBo(hoTen, khoa, trinhDo, phuCap, soTietDay, heSoLuong);
        danhSachCanBo.add(canBo);
    }
    
    private static void nhapThongTinNhanVien(String hoTen, Scanner scanner) {
        System.out.print("Phong ban: ");
        String phongBan = scanner.nextLine();
    
        System.out.print("Chuc vu (truong phong, pho phong, nhan vien): ");
        String chucVu = scanner.nextLine();
    
        System.out.print("So ngay cong: ");
        int soNgayCong = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("He so luong: ");
        float heSoLuong = scanner.nextFloat();
        scanner.nextLine();
    
        int phuCap = tinhPhuCapNhanVien(chucVu);
        CanBo canBo = new CanBo(hoTen, phongBan, chucVu, phuCap, soNgayCong, heSoLuong);
        danhSachCanBo.add(canBo);
    }
    
    private static int tinhPhuCapGiangVien(String trinhDo) {
        switch (trinhDo) {
            case "cu nhan":
                return 300;
            case "thac si":
                return 500;
            case "tien si":
                return 1000;
            default:
                return 0;
        }
    }
    
    private static int tinhPhuCapNhanVien(String chucVu) {
        switch (chucVu) {
            case "truong phong":
                return 2000;
            case "pho phong":
                return 1000;
            case "nhan vien":
                return 500;
            default:
                return 0;
        }
    }

    private static void xuatDanhSachNhanVien(Scanner scanner) {
        System.out.print("Nhap ten phong ban: ");
        String tenPhongBan = scanner.nextLine();
    
        System.out.println("\n--- Danh sach nhan vien phong ban " + tenPhongBan + " ---");
        for (CanBo canBo : danhSachCanBo) {
            if (canBo.getKhoaPhongBan().equalsIgnoreCase(tenPhongBan)) {
                System.out.println(canBo);
            }
        }
    }

    private static void tinhTongLuong() {
        float tongLuong = 0;
        for (CanBo canBo : danhSachCanBo) {
            float luong = tinhLuongCanBo(canBo);
            tongLuong += luong;
        }
    
        System.out.println("\nTong so luong: " + tongLuong);
    }
    
    private static float tinhLuongCanBo(CanBo canBo) {
        if (canBo.getTrinhDo().equalsIgnoreCase("giang vien")) {
            return canBo.getHeSoLuong() * 730 + canBo.getPhuCap() + canBo.getSoTietDaySoNgayCong() * 45;
        } else {
            return canBo.getHeSoLuong() * 730 + canBo.getPhuCap() + canBo.getSoTietDaySoNgayCong() * 30;
        }
    }

    private static void sapXepCanBo() {
        Collections.sort(danhSachCanBo, new Comparator<CanBo>() {
            @Override
            public int compare(CanBo canBo1, CanBo canBo2) {
                float luong1 = tinhLuongCanBo(canBo1);
                float luong2 = tinhLuongCanBo(canBo2);
    
                if (luong1 > luong2) {
                    return -1;
                } else if (luong1 < luong2) {
                    return 1;
                } else {
                    return canBo1.getHoTen().compareToIgnoreCase(canBo2.getHoTen());
                }
            }
        });
    
        System.out.println("\nDanh sach can bo da duoc sap xep theo ten va luong:");
        for (CanBo canBo : danhSachCanBo) {
            System.out.println(canBo);
        }
    }
}    
    
