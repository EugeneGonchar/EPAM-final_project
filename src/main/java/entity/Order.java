package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order implements Entity {
    private int id;
    private int userId;
    private int carId;
    private Timestamp dateReceived;
    private Timestamp returnDate;
    private int pickupAddressId;
    private int dropoffAddressId;
    private BigDecimal totalCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Timestamp getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Timestamp dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public int getPickupAddressId() {
        return pickupAddressId;
    }

    public void setPickupAddressId(int pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public int getDropoffAddressId() {
        return dropoffAddressId;
    }

    public void setDropoffAddressId(int dropoffAddressId) {
        this.dropoffAddressId = dropoffAddressId;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
