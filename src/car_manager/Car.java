package car_manager;

/**
 * Lớp Car đại diện cho một chiếc xe với các thông tin chi tiết như ID xe, màu
 * sắc, ID khung xe, ID động cơ và hãng. Lớp này triển khai giao diện Comparable
 * để có thể so sánh các đối tượng Car theo tên hãng (brand) và ID xe (carID).
 */
public class Car implements Comparable<Car> {

    private String carID, color, frameID, engineID;
    public Brand brand;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {   //Hàm khởi tạo với các tham số để khởi tạo đối tượng Car với thông tin.
        this.carID = carID; // carID   mã xe (ID của xe)
        this.brand = brand; // brand   hãng xe
        this.color = color; // color   màu sắc xe
        this.frameID = frameID;// frameID ID khung xe
        this.engineID = engineID; // engineID ID động cơ
    }

    // Trả về đối tượng 
    public Brand getBrand() {
        return brand;
    }

    public String getCarID() {
        return carID;
    }

    public String getFrameID() {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setUpdatedCar(Brand brand, String color, String frameID, String engineID) {  // Cập nhật thông tin của đối tượng Car 
        this.brand = brand; // brand   hãng xe mới
        this.color = color; // color    màu sắc mới
        this.frameID = frameID;//frameID  ID khung xe mới
        this.engineID = engineID;//engineID ID động cơ mới
    }

    /**
     * Tạo chuỗi chứa thông tin Car hiển thị.
     *
     * @return Chuỗi chứa thông tin hãng xe, mã xe, màu sắc, ID khung và ID động
     * cơ.
     */
    public String screenString() {
        return brand + "\n" + carID + "," + color + "," + frameID + "," + engineID;
    }

    /**
     * So sánh đối tượng Car hiện tại với đối tượng Car khác . Giá trị nguyên
     * thể hiện kết quả so sánh. Giá trị dương nếu tên hãng lớn hơn, giá trị âm
     * nếu nhỏ hơn, 0 nếu bằng nhau.
     */
    @Override
    public int compareTo(Car car) {
        int val = this.getBrand().getBrandName().compareTo(car.getBrand().getBrandName());
        // Nếu tên hãng giống nhau, so sánh tiếp theo mã xe
        if (val == 0) {
            val = this.getCarID().compareTo(car.getCarID());
        }
        return val;
    }

    /**
     * Tạo chuỗi chứa thông tin của đối tượng Car để ghi vào file.
     *
     * @return Chuỗi chứa thông tin mã xe, ID hãng, màu sắc, ID khung và ID động
     * cơ.
     */
    @Override
    public String toString() {
        return carID + "," + brand.getBrandID() + "," + color + "," + frameID + "," + engineID;
    }
}
