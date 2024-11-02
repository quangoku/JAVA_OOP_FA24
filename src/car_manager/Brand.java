/**
 * @author LongTV
 */

package car_manager;

public class Brand {
    private String brandID, brandName, soundBrand;
    private double price;

    Brand () {

    }

    //Hàm khởi tạo với các tham số để khởi tạo đối tượng Brand

    Brand (String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;                   //ID hãng
        this.brandName = brandName;               //Tên hãng
        this.soundBrand = soundBrand;             //Âm thanh hãng
        this.price = price;                       //Giá spham
    }
    
    public String getBrandID () {
        return brandID;
    }

    public String getBrandName () {
        return brandName;
    }


    // Cập nhập thông tin đối tượng Brand

    public void setUpdatedBrand (String brandName, String soundBrand, double price) {
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    // Tạo chuỗi chứa thông tin các đặc điểm của Brand để hiển thị
    @Override
    public String toString () {
        return brandID + "," + brandName + "," + soundBrand + ":" + price; 
    }
}
