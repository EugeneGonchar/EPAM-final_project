package service;

import dto.CarDTO;
import dto.OrderDTO;
import entity.Car;

import java.util.List;

public interface CarService {

    public List<Car> getCarList();

    public List<Car> getFreeCarList(OrderDTO orderDTO);

    public Car getCar(CarDTO carDTO);

}
