package dto;

import entity.Accident;
import entity.Address;
import entity.Car;
import entity.Order;

public class FullOrderDTO {

    private Order order;
    private Car car;
    private Address pickupAddress;
    private Address dropoffAddress;
    private Accident[] accidents;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Address getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(Address dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    public Accident[] getAccidents() {
        return accidents;
    }

    public void setAccidents(Accident[] accidents) {
        this.accidents = accidents;
    }
}
