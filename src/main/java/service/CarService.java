package service;

import domain.dto.CarDTO;
import domain.dto.OrderDTO;
import domain.dto.PageDTO;
import domain.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getCarList(PageDTO pageDTO);

    List<Car> getFreeCarList(OrderDTO orderDTO, PageDTO pageDTO);

    Car getCar(CarDTO carDTO);

    void updateCarImg(int carId, String fileName);

}
