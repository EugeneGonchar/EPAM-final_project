package dao.impl;

import static dao.util.DBFieldName.*;

import dao.CarDAO;
import dao.exception.dao.DAOException;
import dao.util.DBFieldName;
import dao.util.DomainCreator;
import dao.util.QueryBuilder;
import domain.dto.PageDTO;
import domain.entity.Car;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarDAOImpl extends CarDAO{

    private static final String SELECT_CARS_COUNT = "SELECT COUNT(*) AS `count` FROM `car`";

    private static final String SELECT_FREE_CARS_COUNT = "SELECT COUNT(*) AS `count`\n" +
            "FROM `car`\n" +
            "WHERE `car_id` NOT IN(SELECT `car`.`car_id`\n" +
            "FROM `car` \n" +
            "JOIN `order`\n" +
            "ON `order`.`car_id` = `car`.`car_id`\n" +
            "WHERE (? BETWEEN DATE_SUB(`date_received`, INTERVAL 2 HOUR) AND DATE_ADD(`return_date`, INTERVAL 2 HOUR)) OR\n" +
            "(`date_received` BETWEEN DATE_SUB(?, INTERVAL 2 HOUR) AND DATE_ADD(?, INTERVAL 2 HOUR))\n" +
            "GROUP BY `car_id`)";

    private static final String SELECT_CARS = "SELECT `car`.car_id, `car`.`image`, `brand`.`name` AS `brand`, `model`.`name` AS `model`, `car_class`.`name` AS `car_class`, `seats`, `doors`, `air_conditioning`, `automatic_gearbox`, `rental_value_for_day`, `fuel_consumption`, `color`, `year_of_issue`, `type` AS `engine_type`\n" +
            "FROM `car` \n" +
            "JOIN `model`\n" +
            "ON `car`.model_id = `model`.model_id\n" +
            "JOIN `brand`\n" +
            "ON `model`.brand_id = `brand`.brand_id\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.car_class_id = `car`.car_class_id\n"+
            "JOIN `engine`\n"+
            "ON `engine`.`engine_id` = `car`.`engine_id`" +
            "ORDER BY `brand`, `model`";

    private static final String SELECT_FREE_CARS = "SELECT `car`.`car_id`, `car`.`image`, `brand`.`name` AS `brand`, `model`.`name` AS `model`, `car_class`.`name` AS `car_class`, `seats`, `doors`, `air_conditioning`, `automatic_gearbox`, `rental_value_for_day`, `fuel_consumption`, `color`, `year_of_issue`, `type` AS `engine_type`\n" +
            "FROM `car`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "JOIN `engine`\n"+
            "ON `engine`.`engine_id` = `car`.`engine_id`\n"+
            "WHERE `car_id` NOT IN(SELECT `car`.`car_id`\n" +
            "FROM `car` \n" +
            "JOIN `order`\n" +
            "ON `order`.`car_id` = `car`.`car_id`\n" +
            "WHERE (? BETWEEN DATE_SUB(`date_received`, INTERVAL 2 HOUR) AND DATE_ADD(`return_date`, INTERVAL 2 HOUR)) OR\n" +
            "(`date_received` BETWEEN DATE_SUB(?, INTERVAL 2 HOUR) AND DATE_ADD(?, INTERVAL 2 HOUR))\n" +
            "GROUP BY `car_id`)\n" +
            "ORDER BY `rental_value_for_day`";

    private static final String SELECT_CAR_BY_ID = "SELECT `car`.`car_id`, `car`.`image`, `brand`.`name` AS `brand`, `model`.`name` AS `model`, `car_class`.`name` AS `car_class`, `seats`, `doors`, `air_conditioning`, `automatic_gearbox`, `rental_value_for_day`, `fuel_consumption`, `color`, `year_of_issue`, `type` AS `engine_type`\n" +
            "FROM `car`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "JOIN `engine`\n"+
            "ON `engine`.`engine_id` = `car`.`engine_id`\n"+
            "WHERE `car_id` = ?";

    private static final String UPDATE_CAR_IMAGE_BY_CAR_ID = "UPDATE `car` SET `image` = ? WHERE `car_id` = ?";

    @Override
    public List<Car> getAll() throws DAOException{
        List<Car> carList = new LinkedList<>();
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARS)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                car = DomainCreator.createCar(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving list of cars", e);
        }
        return carList;
    }

    @Override
    public int getCarsCount() throws DAOException{
        return getElementsCount(SELECT_CARS_COUNT);
    }

    @Override
    public List<Car> findAll(PageDTO pageDTO) throws DAOException {
        List<Car> carList = new LinkedList<>();
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_CARS, pageDTO))){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                car = DomainCreator.createCar(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving list of cars", e);
        }
        return carList;
    }

    @Override
    public int getFreeCarsCount(Timestamp dateReceived, Timestamp returnDate) throws DAOException{
        int count = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FREE_CARS_COUNT)){
            preparedStatement.setTimestamp(1, dateReceived);
            preparedStatement.setTimestamp(2, dateReceived);
            preparedStatement.setTimestamp(3, returnDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                count = resultSet.getInt(DBFieldName.FIELD_COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving count of free cars", e);
        }
        return count;
    }

    @Override
    public List<Car> getFreeCars(Timestamp dateReceived, Timestamp returnDate, PageDTO pageDTO) throws DAOException{
        List<Car> carList = new LinkedList<>();
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_FREE_CARS, pageDTO))){
            preparedStatement.setTimestamp(1, dateReceived);
            preparedStatement.setTimestamp(2, dateReceived);
            preparedStatement.setTimestamp(3, returnDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                car = DomainCreator.createCar(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving list of free cars", e);
        }
        return carList;
    }

    @Override
    public Car getCarById(int id) throws DAOException{
        Car car = new Car();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                car = DomainCreator.createCar(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving car by id", e);
        }

        return car;
    }

    @Override
    public void updateImageByCarId(int id, String fileName) throws DAOException{
        updateImageNameById(UPDATE_CAR_IMAGE_BY_CAR_ID, fileName, id);
    }
}
