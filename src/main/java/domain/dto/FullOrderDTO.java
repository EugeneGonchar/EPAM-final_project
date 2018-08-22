package domain.dto;

import domain.entity.*;

import java.util.Arrays;

public class FullOrderDTO {

    private Order order;
    private Car car;
    private Address pickupAddress;
    private Address dropoffAddress;
    private Accident[] accidents;
    private OrderStatus orderStatus;

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "FullOrderDTO{" +
                "order=" + order +
                ", car=" + car +
                ", pickupAddress=" + pickupAddress +
                ", dropoffAddress=" + dropoffAddress +
                ", accidents=" + Arrays.toString(accidents) +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
