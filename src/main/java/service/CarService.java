package service;

import dto.OrderDTO;
import entity.Car;

import java.util.List;

public interface CarService {

    public List<Car> getCarList();

    public List<Car> getFreeCarList(OrderDTO orderDTO);

}
