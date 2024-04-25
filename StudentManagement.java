import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagement {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("****************************************");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Cập nhật thông tin sinh viên theo ID");
            System.out.println("3. Xóa sinh viên theo ID");
            System.out.println("4. Xắp xếp sinh viên theo tên");
            System.out.println("5. Hiển thị danh sách sinh viên");
            System.out.println("6. Kết thúc chương trình");
            System.out.println("****************************************");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    sortStudentsByName();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    System.out.println("Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Nhập ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();
        System.out.print("Nhập điểm trung bình: ");
        double gpa = scanner.nextDouble();

        Student student = new Student(id, name, age, address, gpa);
        students.add(student);
        System.out.println("Thêm sinh viên thành công!");
    }

    private static void updateStudent() {
        System.out.print("Nhập ID của sinh viên cần cập nhật: ");
        int id = scanner.nextInt();
        int index = findStudentIndexById(id);
        if (index != -1) {
            Student student = students.get(index);
            System.out.print("Nhập tên mới: ");
            String name = scanner.next();
            student.setName(name);
            System.out.print("Nhập tuổi mới: ");
            int age = scanner.nextInt();
            student.setAge(age);
            System.out.print("Nhập địa chỉ mới: ");
            String address = scanner.next();
            student.setAddress(address);
            System.out.print("Nhập điểm trung bình mới: ");
            double gpa = scanner.nextDouble();
            student.setGpa(gpa);
            System.out.println("Cập nhật thông tin sinh viên thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên có ID này!");
        }
    }

    private static void deleteStudent() {
        System.out.print("Nhập ID của sinh viên cần xóa: ");
        int id = scanner.nextInt();
        int index = findStudentIndexById(id);
        if (index != -1) {
            students.remove(index);
            System.out.println("Xóa sinh viên thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên có ID này!");
        }
    }

    private static void sortStudentsByName() {
        Collections.sort(students, Comparator.comparing(Student::getName));
        System.out.println("Sắp xếp sinh viên theo tên thành công!");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
        } else {
            System.out.println("Danh sách sinh viên:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static int findStudentIndexById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
