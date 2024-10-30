
package car_manager;

public class Car implements Comparable <Car> {
    
    private String carID, color, frameID, engineID;
    public Brand brand; 
    
    public Car () {

    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public Brand getBrand () {
        return brand;
    }

    public String getCarID () {
        return carID;
    }
    
    public String getFrameID () {
        return frameID;
    }

    public String getEngineID() {
        return engineID;
    }
    
    public void setUpdatedCar(Brand brand, String color, String frameID, String engineID) {
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String screenString () {
        
        return brand + "\n" + carID + "," + color + "," + frameID + "," + engineID;
    }

    
    @Override
    public int compareTo (Car car) {
        int value = this.getBrand().getBrandName().compareTo(car.getBrand().getBrandName());
        if (value == 0) {
            value = this.getCarID().compareTo(car.getCarID());
        }
        return value;
    }

    
    
    @Override
    public String toString () {
        return carID + "," + brand.getBrandID() + "," + color + "," + frameID + "," + engineID;
    }




}
