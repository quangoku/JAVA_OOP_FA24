package car_manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lớp CarManager là chương trình chính để quản lý các xe hơi (Car) và các hãng xe (Brand).
 * Cung cấp menu cho người dùng để thực hiện các thao tác như thêm, xóa, cập nhật, và liệt kê thông tin xe và hãng xe.
 */
public class CarManager {
    public static void main(String[] args) throws IOException {
        int choice; // Biến lưu lựa chọn của người dùng từ menu
        boolean checkSuccessful; // Biến lưu trạng thái thành công của các thao tác
        String fileCarsName = "E:\\JAVAWORKSHOP\\CAR_MANAGER\\src\\car_manager\\cars.txt";
        String fileBrandsName = "E:\\JAVAWORKSHOP\\CAR_MANAGER\\src\\car_manager\\brands.txt";

        // Khởi tạo một đối tượng BrandList trống để chứa các hãng xe
        BrandList brandList = new BrandList();

        // Khởi tạo một đối tượng CarList trống, liên kết với brandList
        CarList carList = new CarList(brandList);

        // Tải dữ liệu các hãng xe từ file vào brandList
        brandList.loadFromFile(fileBrandsName);

        // Tải dữ liệu các xe từ file vào carList
        carList.loadFromFile(fileCarsName);

        String bID, brandCarID  ; // Các biến lưu thông tin ID của hãng và xe
        
        // Khởi tạo một ArrayList chứa các lựa chọn trong menu của chương trình
        ArrayList<String> ops = new ArrayList<>(11);
        ops.add("1 - List all brands");
        ops.add("2 - Add a new brand");
        ops.add("3 - Search a brand based on its ID");
        ops.add("4 - Update a brand");
        ops.add("5 - Save brands to the file, named brands.txt");
        ops.add("6 - List all cars in ascending order of brand names");
        ops.add("7 - List cars based on a part of an input brand name");
        ops.add("8 - Add a car");
        ops.add("9 - Remove a car based on its ID");
        ops.add("10 - Update a car based on its ID");
        ops.add("11 - Save cars to file, named cars.txt");

        Menu menu = new Menu();

        // Vòng lặp để hiển thị menu và xử lý lựa chọn của người dùng cho đến khi người dùng chọn thoát.
        do {
            choice = menu.int_getChoice(ops); // Lấy lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Lựa chọn 1: Liệt kê tất cả các hãng xe
                    brandList.listBrands();
                    break;
                case 2:
                    // Lựa chọn 2: Thêm một hãng xe mới vào danh sách
                    brandList.addBrand();
                    break;
                case 3:
                    // Lựa chọn 3: Tìm kiếm một hãng xe dựa trên ID
                    System.out.print("Input brand ID: ");
                    bID = new Scanner(System.in).nextLine();
                    if (brandList.searchID(bID) == -1) {
                        System.out.println("Brand ID not found !");
                    } else {
                        System.out.println(brandList.get(brandList.searchID(bID)).toString());
                    }
                    break;
                case 4:
                    // Lựa chọn 4: Cập nhật thông tin của một hãng xe
                    brandList.updateBrand();
                    break;
                case 5:
                    // Lựa chọn 5: Lưu danh sách các hãng xe vào file brands.txt
                    brandList.saveToFile(fileBrandsName);
                    break;
                case 6:
                    // Lựa chọn 6: Liệt kê tất cả các xe theo thứ tự tăng dần của tên hãng
                    carList.listCars();
                    break;
                case 7:
                    // Lựa chọn 7: Tìm kiếm các xe dựa trên một phần của tên hãng được nhập
                    System.out.print("Input brand: ");
                    String brandPartName = new Scanner(System.in).nextLine();
                    carList.printBasedBrandName(brandPartName);
                    break;
                case 8:
                    // Lựa chọn 8: Thêm một xe mới vào danh sách
                    carList.addCar();
                    break;
                case 9:
                    // Lựa chọn 9: Xóa một xe dựa trên ID của xe
                    checkSuccessful = carList.removeCar();
                    if (checkSuccessful) {
                        System.out.println("Car removed successfully !");
                    } else {
                        System.out.println("Car removed unsuccessfully !");
                    }
                    break;
                case 10:
                    // Lựa chọn 10: Cập nhật thông tin của một xe dựa trên ID
                    checkSuccessful = carList.updateCar();
                    if (checkSuccessful) {
                        System.out.println("Car updated successfully !");
                    } else {
                        System.out.println("Car updated unsuccessfully !");
                    }
                    break;
                case 11:
                    // Lựa chọn 11: Lưu danh sách các xe vào file cars.txt
                    carList.saveToFile(fileCarsName);
                    break;
            }
        } while ((choice > 0) && (choice <= 11)); // Thoát vòng lặp khi người dùng nhập ngoài phạm vi từ 1 đến 11
    }
}