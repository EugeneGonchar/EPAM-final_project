package service.impl;

import dao.CarDAO;
import dao.Transaction;
import dao.exception.dao.DAOException;
import dao.factory.DAOFactory;
import domain.dto.CarDTO;
import domain.dto.OrderDTO;
import domain.dto.PageDTO;
import domain.entity.Car;
import service.CarService;
import service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCarList(PageDTO pageDTO) throws ServiceException {
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        try {
            pageDTO.setElementsCount(carDAO.getCarsCount());
            pageDTO.calculatePagesCount();
            carList = carDAO.findAll(pageDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving list of cars", e);
        } finally {
            transaction.endTransaction();
        }
        return carList;
    }

    @Override
    public List<Car> getFreeCarList(OrderDTO orderDTO, PageDTO pageDTO) throws ServiceException{
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        Transaction transaction = new Transaction();
        List<Car> carList = null;

        transaction.beginTransaction(carDAO);

        try {
            pageDTO.setElementsCount(carDAO.getFreeCarsCount(orderDTO.getDateReceived(), orderDTO.getReturnDate()));
            pageDTO.calculatePagesCount();
            carList = carDAO.getFreeCars(orderDTO.getDateReceived(), orderDTO.getReturnDate(), pageDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving list of free cars", e);
        } finally {
            transaction.endTransaction();
        }
        return carList;
    }

    @Override
    public Car getCar(CarDTO carDTO) throws ServiceException{
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
        Car car = null;
        Transaction transaction = new Transaction();

        transaction.beginTransaction(carDAO);

        try {
            car = carDAO.getCarById(carDTO.getId());
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving car", e);
        } finally {
            transaction.endTransaction();
        }

        return car;
    }

    @Override
    public void updateCarImg(int carId, String fileName) throws ServiceException{
        CarDAO carDAO = DAOFactory.getInstance().getCarDAO();

        Transaction transaction = new Transaction();

        transaction.beginTransaction(carDAO);

        try {
            carDAO.updateImageByCarId(carId, fileName);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating car's image", e);
        } finally {
            transaction.endTransaction();
        }
    }
}
