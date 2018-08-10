package service.impl;

import dao.Transaction;
import dao.impl.CarDAO;
import pojo.dto.CarDTO;
import pojo.dto.OrderDTO;
import pojo.entity.Car;
import service.CarService;

import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCarList(){
        CarDAO carDAO = new CarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        carList = carDAO.findAll();

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return carList;
    }

    @Override
    public List<Car> getFreeCarList(OrderDTO orderDTO){
        CarDAO carDAO = new CarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        carList = carDAO.findAllFreeCars(orderDTO.getDateReceived(), orderDTO.getReturnDate());

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
