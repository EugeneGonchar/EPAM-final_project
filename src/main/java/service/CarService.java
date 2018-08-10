package service;

import pojo.dto.CarDTO;
import pojo.dto.OrderDTO;
import pojo.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getCarList();

    List<Car> getFreeCarList(OrderDTO orderDTO);

    Car getCar(CarDTO carDTO);

}
