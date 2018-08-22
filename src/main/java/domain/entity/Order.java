package domain.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order implements Entity {
    private static final String SECONDS = ":00";

    private int id;
    private int userId;
    private int carId;
    private Timestamp dateReceived;
    private Timestamp returnDate;
    private int pickupAddressId;
    private int dropoffAddressId;
    private BigDecimal totalCost;
    private int statusId;

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

    public void setDateReceived(String dateReceived) {
        this.dateReceived = Timestamp.valueOf(dateReceived+SECONDS);
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = Timestamp.valueOf(returnDate+SECONDS);
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Order order = (Order) o;

        if (getId() != order.getId()){
            return false;
        }
        if (getUserId() != order.getUserId()){
            return false;
        }
        if (getCarId() != order.getCarId()){
            return false;
        }
        if (getPickupAddressId() != order.getPickupAddressId()){
            return false;
        }
        if (getDropoffAddressId() != order.getDropoffAddressId()){
            return false;
        }
        if (getStatusId() != order.getStatusId()){
            return false;
        }
        if (getDateReceived() != null ? !getDateReceived().equals(order.getDateReceived()) : order.getDateReceived() != null){
            return false;
        }

        if (getReturnDate() != null ? !getReturnDate().equals(order.getReturnDate()) : order.getReturnDate() != null){
            return false;
        }
        return getTotalCost() != null ? getTotalCost().equals(order.getTotalCost()) : order.getTotalCost() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 37 * result + getUserId();
        result = 37 * result + getCarId();
        result = 37 * result + (getDateReceived() != null ? getDateReceived().hashCode() : 0);
        result = 37 * result + (getReturnDate() != null ? getReturnDate().hashCode() : 0);
        result = 37 * result + getPickupAddressId();
        result = 37 * result + getDropoffAddressId();
        result = 37 * result + (getTotalCost() != null ? getTotalCost().hashCode() : 0);
        result = 37 * result + getStatusId();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", carId=" + carId +
                ", dateReceived=" + dateReceived +
                ", returnDate=" + returnDate +
                ", pickupAddressId=" + pickupAddressId +
                ", dropoffAddressId=" + dropoffAddressId +
                ", totalCost=" + totalCost +
                ", statusId=" + statusId +
                '}';
    }
}
