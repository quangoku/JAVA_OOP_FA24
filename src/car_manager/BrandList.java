package car_manager;

import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand> {

    private String brandID, brandName, soundBrand;
    private double price;
    private int pos;

    Scanner scanner = new Scanner(System.in);

    PrintWriter pw;
    BufferedReader br;

    public boolean loadFromFile(String fileName) {                              // filename là đường dẫn của file txt
        try {
            br = new BufferedReader(new FileReader(fileName));            // tạo bufferreader để đọc file
            String[] arr;                                                       // mảng để lưu các giá trị đọc vào dưới dạng string
            String line = br.readLine();                                        // đọc từng dòng trong file txt
            while ((line != null)) {                                            // kiểm tra điều kiện kết thúc file 
                arr = line.split(",");                                     // split file theo regex ","
                brandID = arr[0].trim();                                        // brandID được gắn vào thành phần 0
                brandName = arr[1].trim();                                      // brandname được gắn vào thành phần 1
                soundBrand = arr[2].split(":")[0].trim();                   // chia soundbrand và price theo regex ":" (soundbrand:price) 
                price = Double.parseDouble(arr[2].split(":")[1].trim()); // gán giá trị price
                this.add(new Brand(brandID, brandName, soundBrand, price));     // add brand mới với các giá trị đã đọc được từ file txt
                line = br.readLine();                                           // dọc dòng tiếp theo 
            }
            br.close();                                                         // giải phóng sau khi đọc xong file 
            return true;                                                        // return true nếu đọc file thành công 
        } catch (Exception e) {
            System.out.println("File " + fileName + " not found !");
        }
        return false;                                                           // báo lỗi nếu đọc file ko thành công  và trả về false
    }

    public boolean saveToFile(String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));               // tạo biến pw để lưu dữ liệu vào file 
            for (Brand i : this) {
                pw.println(i);                                                // in ra theo từng dòng
            }
            pw.close();                                                         // đóng file sau khi viết xong 
            System.out.println("DONE SAVE");
            return true;
        } catch (IOException e) {
            e.printStackTrace();                                                // báo lỗi nếu không mở file thành không
        }
        return false;
    }

    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++) {
            if (bID.equals(this.get(i).getBrandID())) {             // tìm kiếm dựa trên ID    
                return i;                                                        // trả về vị trí của phần tử nếu tìm tháy
            }
        }
        return -1;                                                               // trả về -1 nếu không tìm thấy 
    }

    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    public void addBrand() {
        boolean checkBrandID = false;                                               // biến kiểm tra xem Id nhập vào có hợp lệ
        do {
            System.out.print("Input brand ID: ");
            brandID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (brandID.equals(this.get(i).getBrandID())) {         // kiểm tra xem id đã tồn tại chưa 
                    checkBrandID = true;
                    System.out.println("This brand ID is existed. Try another one!");
                    break;
                } else {
                    checkBrandID = false;
                }
            }
        } while (checkBrandID == true);

        do {
            System.out.print("Input brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {                           // kiểm tra tính hợp lệ của brand name

                break;
            }
            System.out.println("The brand name must not be null. Try again !");
        } while (true);

        do {
            System.out.print("Input sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {                           // kiểm tra tính hợp lệ của sound
                break;
            }
            System.out.println("The sound brand must not be null. Try again !");
        } while (true);

        do {
            System.out.print("Input price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {                                                   // kiểm tra tính hợp lệ của price 
                    System.out.println("The price must not be null. Try again !");
                }
            } catch (NumberFormatException e) {
                System.out.println("The price must be a number. Try again !");
                price = 0;
            }
        } while (price == 0);
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("Brand has added successfully");
    }

    public void updateBrand() {
        do {
            System.out.print("Input brand ID: ");
            brandID = scanner.nextLine();
            pos = searchID(brandID);                                         // tìm kiếm id có tồn tại không
            if (pos != -1) {
                break;
            }
            System.out.println("Not found !");
        } while (true);

        do {
            System.out.print("Input brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("The brand name can not be null. Try again !");
        } while (true);

        do {
            System.out.print("Input sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("The sound brand can not be null. Try again !");
        } while (true);

        do {
            System.out.print("Input price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0) {
                    System.out.println("The price can not be null. Try again !");
                    price = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("The price must be a number. Try again !");
                price = 0;
            }
        } while (price == 0);

        this.get(pos).setUpdatedBrand(brandName, soundBrand, price);
        System.out.println("Brand has updated successfully !");
    }

    public void listBrands() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));                             // liệt kê các brand
        }
    }
}
