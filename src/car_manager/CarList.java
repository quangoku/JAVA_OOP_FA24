package car_manager;
// import thư viện
import java.io.*;
import java.util.*;
// tạo lớp carlist thực chât là list thừa hưởng từ một ArrayLsit của kiểu Object car
public class CarList extends ArrayList <Car> {
    
    // khởi tạo các giá trị cần thiết cho list
    private String carID, color, frameID, engineID;
    
    // brand đã được khởi tạo từ lớp brand đã có
    private Brand brand;
    
    //khởi tạo lớp menu để sử dụng các hàm accept input values from stdin
    Menu menu = new Menu();
    
    //khởi tạo main scanner chính cho lớp
    Scanner scanner = new Scanner (System.in);
    
    //khởi tạo brandList từ lớp đã có để có thể lấy dữ liệu về hãng xe
    BrandList brandList;
    
    //khởi tạo bufferReader phục vụ thao tác read/write trên file
    BufferedReader br;
    
    // tạo một string và môt array of string làm buffer trong việc readline của bufferReader
    String line;
    String [] arr;
    
    //tạo 1 constructor cho carlist với dữa liệu vào là các thông tin mang ID của brand
    public CarList (BrandList bList) {
        brandList = bList;
    }
    
    // buferReader thực hiện read file car đã được locate tại carManager
    // chạy thử kiểm tra xem file có tồn tại hay không, sử dụng try/catch => if yes, execute the inner code, if no => throws an IO exception to indicate the error
    public boolean loadFromFile (String fileName) throws IOException {
        try {
            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            while (line != null) {
                arr = line.split(",");// split các thông tin trong line ra thành từng chuỗi thông tin nhỏ bởi dấu phảy, lưu trữ trong array
                carID = arr[0].trim();// điền thông tin vào từng mục của list, sử dụng trim để xóa đi các khoảng trắng thừa nếu có thể
                brand = brandList.get(brandList.searchID(arr[1].trim()));// append band ID vào lisr cars
                color = arr[2].trim();
                frameID = arr[3].trim();
                engineID = arr[4].trim();
                // add new car vào list
                this.add(new Car(carID, brand, color, frameID, engineID));
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {// nếu không tìm được file, throws an exception
            System.out.println("File not found !");
        }
        return false;
    }

    //open the file based on the filename to write data line-by-line in plaintext format
    public boolean saveToFile (String fileName) {
        try {
            // tạo printWWriter phục vụ cho quá trình viết file
            PrintWriter pw = new PrintWriter(new FileWriter (fileName));
            for (Car i: this) {
                pw.println(i); // append info từ lớp car => this will trigger the toString funtion in the original car class so It can write into the text file
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    //tìm kiếm car dựa trên ID của nó, if found then return the ID that indicates the index number of the car within the list
    public int searchID (String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getCarID())) {
                return i;
            }
        }
        return -1;
    }
    
    //search car dựa theo frame ID, dùng để check có item car nào bị lặp lại trong arrayList không
    private int searchEngineID(String searchEngineID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchEngineID.equals(this.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    //tương tự như 2 methood ở trên
    private int searchFrameID(String searchFrameID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchFrameID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    //thêm một car vào arraylist này (this)
    public void addCar() {
        boolean checkCarID;
        boolean isRightFormat;
        do {
            
            //reset 2 boolean sau mỗi vòng lặp
            checkCarID = false; // giả sử như ID không trùng lặp, đặt giá trị ban đầu là false
            isRightFormat = true;// giả sử như format đúng
            
            System.out.print("Input car ID: ");
            carID = scanner.nextLine();// input ID to check if guarantee some onditions
            
            if (!carID.matches("C[0-9][0-9]")) {
                isRightFormat = false; // nếu không đúng format, set boolean to false and prompt message to the user
                System.out.println("Invalid ID format, the car ID must be in the C00 (2x number from 1 to 9) format");
            }
            //check ID xem có trùng lặp không
            for (Car car : this) {
                if (carID.equals(car.getCarID())) {
                    checkCarID = true;
                    System.out.println("This car ID is existed. Try another one!");
                    break;
                } else {
                    checkCarID = false;
                }
            }
        } while (checkCarID == true || !isRightFormat);

        //tạo menu danh sach các brand hiện có
        Brand brand = menu.ref_getChoice(brandList);
            
        do {
            System.out.print("Input color: "); // imput thông tin
            color = scanner.nextLine();
            if (color.equals("") != true) { // ngăn string rỗng
                break;
            }
            System.out.println("The color must not be null. Try again !");
        } while (true);
        
        
        do {
            System.out.print("Input frame ID: "); // guarantee that the frame ID is being imported in the right form
            frameID = scanner.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                break;
            }
            System.out.println("The frame ID must be in F00000 (5x number from 0 to 9) format and not be duplicated. Try again !");
        } while (true);
        
        
        do {
            System.out.print("Input engine ID: "); // guarantee that the engine ID is being imported in the right form
            engineID = scanner.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                break;
            }
            System.out.println("The engine ID must be in E00000 (5x number from 0 to 9) format and not be duplicated. Try again !");
        } while (true);
        
        // nếu đã vượt qua tất cả các tiêu chuẩn ở trên, đã đủ điều kiện để tạo 1 car
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car has added successfully !");
    }

 /// hàm dùng để debug check xem add object đúng chưa
    public void printBasedBrandName (String target) {
//        String target;
        int count = 0;
//        System.out.println("Input brand name: ");
//        target = scanner.nextLine();
        for (Car car : this) {
            if (car.brand.getBrandName().contains(target)) {
                System.out.println(car.screenString());
                count ++;
            }
        }
            if (count == 0) {
                System.out.println("No car is detected !");
            }
    }

    //xóa car dựa trên ID
    public boolean removeCar() {
        int pos;
        String targetID;
        System.out.print("Input car ID to removed: ");
        targetID = scanner.nextLine();
        pos = searchID(targetID);
        if (pos >= 0) {
            this.remove(pos);
            return true; // xóa thành công
        }
        return false; // không tìm thấy index
    }

    //Update a car based on it’s ID
    public boolean updateCar () {
        int pos;
        String updatedID;
        System.out.print("Input car ID to updated: ");
        updatedID = scanner.nextLine();
        pos = searchID(updatedID);
        
        
        if (pos >= 0) { // check nếu car ID tồn tại (khác -1)
            Brand brand = menu.ref_getChoice(brandList);
            
            
            //hoạt động tương tự như add car
            do {
                System.out.print("Input color: ");
                color = scanner.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.println("The color must not be null. Try again !");
            } while (true);
            do {
                System.out.print("Input frame ID: ");
                frameID = scanner.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                    break;
                }
                System.out.println("The frame ID must be in F00000 format and not be duplicated. Try again !");
            } while (true);
            do {
                System.out.print("Input engine ID: ");
                engineID = scanner.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                    break;
                }
                System.out.println("The engine ID must be in E00000 format and not be duplicated. Try again !");
            } while (true);
            
            // update car nếu đã pass đủ tiêu chí
            
            this.get(pos).setUpdatedCar (brand, color, frameID, engineID);
            return true;
        } else {
            System.out.println("Car ID not existed !");
        }
        return false;
    }

    //Listing cars in ascending order of brand names
    public void listCars () {
        Collections.sort(this);
        for (Car i: this) {
            System.out.println(i.toString());
        }
    }
}
