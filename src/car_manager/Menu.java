package car_manager;

import java.util.*;


public class Menu {
    private int response;                                                       // Biến lưu trữ phản hồi của người dùng dưới dạng số nguyên
    private Scanner scanner = new Scanner(System.in);                        // Khởi tạo Scanner để đọc dữ liệu từ người dùng

    // Phương thức hiển thị danh sách lựa chọn và lấy lựa chọn từ người dùng
    public int int_getChoice(ArrayList<String> options) {
        for (String i : options) {                                              // Duyệt qua từng lựa chọn trong danh sách
            System.out.println(i);                                            // Hiển thị từng lựa chọn
        }
        System.out.print("Please choose an option 1..11: ");                  // Yêu cầu người dùng chọn một lựa chọn từ 1 đến 11
        response = scanner.nextInt();                                           // Đọc lựa chọn của người dùng và lưu vào biến response
        return response;                                                        // Trả về lựa chọn của người dùng
    }

    // Phương thức lấy lựa chọn của người dùng dưới dạng số nguyên từ danh sách BrandList
    public int int_getChoice(BrandList brand) {
        int n = brand.size();                                                   // Lấy số lượng phần tử trong danh sách brand
        for (int i = 0; i < n; i++) {                                           // Duyệt qua từng phần tử trong danh sách
            System.out.println("" + (i+1) + ". " + brand.get(i));          // Hiển thị mỗi phần tử với số thứ tự
        }
        System.out.print("Please choose an option 1..11: ");                  // Yêu cầu người dùng chọn một lựa chọn từ 1 đến 11
        response = scanner.nextInt();                                           // Đọc lựa chọn của người dùng và lưu vào biến response
        return response;                                                        // Trả về lựa chọn của người dùng
    }

    // Phương thức lấy lựa chọn của người dùng dưới dạng đối tượng Brand từ danh sách
    public Brand ref_getChoice(BrandList options) {
        int N = options.size();                                                 // Lấy số lượng phần tử trong danh sách options
        System.out.println("Brand ID List:");                                 // Hiển thị tiêu đề danh sách các ID của Brand
        do {
            response = int_getChoice(options);                             // Gọi phương thức int_getChoice để lấy lựa chọn của người dùng
        } while ((response < 0) || (response > N));                             // Kiểm tra nếu lựa chọn không hợp lệ (nhỏ hơn 0 hoặc lớn hơn số lượng phần tử)
        return options.get(response - 1);                                       // Trả về đối tượng Brand tương ứng với lựa chọn của người dùng
    }
}