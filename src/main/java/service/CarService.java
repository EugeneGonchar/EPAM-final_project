package service;

import domain.dto.CarDTO;
import domain.dto.OrderDTO;
import domain.dto.PageDTO;
import domain.entity.Car;
import service.exception.ServiceException;

import java.util.List;

public interface CarService {

    List<Car> getCarList(PageDTO pageDTO) throws ServiceException;

    List<Car> getFreeCarList(OrderDTO orderDTO, PageDTO pageDTO) throws ServiceException;

    Car getCar(CarDTO carDTO) throws ServiceException;

    void updateCarImg(int carId, String fileName) throws ServiceException;

}
