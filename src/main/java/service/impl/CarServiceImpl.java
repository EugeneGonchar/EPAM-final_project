package service.impl;

import dao.Transaction;
import dao.impl.CarDAO;
import entity.Car;
import service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCarList(){
        CarDAO carDAO = new CarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        carList = carDAO.findAll();

        transaction.commit();
        transaction.endTransaction();

        return carList;
    }
}
