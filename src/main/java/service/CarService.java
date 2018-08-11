package service;

import pojo.dto.CarDTO;
import pojo.dto.OrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getCarList(PageDTO pageDTO);

    List<Car> getFreeCarList(OrderDTO orderDTO, PageDTO pageDTO);

    Car getCar(CarDTO carDTO);

}
