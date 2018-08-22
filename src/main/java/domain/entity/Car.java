package domain.entity;

import java.math.BigDecimal;

public class Car implements Entity {
    private int id;
    private String brand;
    private String model;
    private short yearOfIssue;
    private byte seats;
    private byte doors;
    private boolean airConditioning;
    private boolean automaticGearbox;
    private BigDecimal rental4Day;
    private String color;
    private double fuelConsumption;
    private String carClass;
    private String engineType;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public short getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(short yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public byte getSeats() {
        return seats;
    }

    public void setSeats(byte seats) {
        this.seats = seats;
    }

    public byte getDoors() {
        return doors;
    }

    public void setDoors(byte doors) {
        this.doors = doors;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isAutomaticGearbox() {
        return automaticGearbox;
    }

    public void setAutomaticGearbox(boolean automaticGearbox) {
        this.automaticGearbox = automaticGearbox;
    }

    public BigDecimal getRental4Day() {
        return rental4Day;
    }

    public void setRental4Day(BigDecimal rental4Day) {
        this.rental4Day = rental4Day;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Car car = (Car) o;

        if (getId() != car.getId()){
            return false;
        }
        if (getYearOfIssue() != car.getYearOfIssue()){
            return false;
        }
        if (getSeats() != car.getSeats()){
            return false;
        }
        if (getDoors() != car.getDoors()){
            return false;
        }
        if (isAirConditioning() != car.isAirConditioning()){
            return false;
        }
        if (isAutomaticGearbox() != car.isAutomaticGearbox()){
            return false;
        }
        if (Double.compare(car.getFuelConsumption(), getFuelConsumption()) != 0){
            return false;
        }
        if (getBrand() != null ? !getBrand().equals(car.getBrand()) : car.getBrand() != null){
            return false;
        }
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null){
            return false;
        }
        if (getRental4Day() != null ? !getRental4Day().equals(car.getRental4Day()) : car.getRental4Day() != null)
            return false;
        if (getColor() != null ? !getColor().equals(car.getColor()) : car.getColor() != null){
            return false;
        }
        if (getCarClass() != null ? !getCarClass().equals(car.getCarClass()) : car.getCarClass() != null){
            return false;
        }
        if (getEngineType() != null ? !getEngineType().equals(car.getEngineType()) : car.getEngineType() != null){
            return false;
        }
        return getImage() != null ? getImage().equals(car.getImage()) : car.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 37 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 37 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 37 * result + (int) getYearOfIssue();
        result = 37 * result + (int) getSeats();
        result = 37 * result + (int) getDoors();
        result = 37 * result + (isAirConditioning() ? 1 : 0);
        result = 37 * result + (isAutomaticGearbox() ? 1 : 0);
        result = 37 * result + (getRental4Day() != null ? getRental4Day().hashCode() : 0);
        result = 37 * result + (getColor() != null ? getColor().hashCode() : 0);
        temp = Double.doubleToLongBits(getFuelConsumption());
        result = 37 * result + (int) (temp ^ (temp >>> 32));
        result = 37 * result + (getCarClass() != null ? getCarClass().hashCode() : 0);
        result = 37 * result + (getEngineType() != null ? getEngineType().hashCode() : 0);
        result = 37 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", seats=" + seats +
                ", doors=" + doors +
                ", airConditioning=" + airConditioning +
                ", automaticGearbox=" + automaticGearbox +
                ", rental4Day=" + rental4Day +
                ", color='" + color + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", carClass='" + carClass + '\'' +
                ", engineType='" + engineType + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
