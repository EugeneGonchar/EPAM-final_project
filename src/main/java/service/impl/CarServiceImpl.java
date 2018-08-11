package service.impl;

import dao.Transaction;
import dao.impl.CarDAO;
import pojo.dto.CarDTO;
import pojo.dto.OrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Car;
import service.CarService;

import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCarList(PageDTO pageDTO){
        CarDAO carDAO = new CarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        pageDTO.setElementsCount(carDAO.getCarsCount());
        pageDTO.calculatePagesCount();
        carList = carDAO.findAll(pageDTO);

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return carList;
    }

    @Override
    public List<Car> getFreeCarList(OrderDTO orderDTO, PageDTO pageDTO){
        CarDAO carDAO = new CarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        pageDTO.setElementsCount(carDAO.getFreeCarsCount(orderDTO.getDateReceived(), orderDTO.getReturnDate()));
        pageDTO.calculatePagesCount();
        carList = carDAO.findFreeCars(orderDTO.getDateReceived(), orderDTO.getReturnDate(), pageDTO);

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return carList;
    }

    @Override
    public Car getCar(CarDTO carDTO){
        CarDAO carDAO = new CarDAO();
        Car car = null;
        Transaction transaction = new Transaction();

        transaction.beginTransaction(carDAO);

        car = carDAO.getCarById(carDTO);

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return car;
    }
}
