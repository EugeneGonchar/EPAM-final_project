package dao.impl;

import dao.AbstractDAO;
import dto.CarDTO;
import entity.Car;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CarDAO extends AbstractDAO {

    private static final String FIND_ALL_CARS = "SELECT `car`.car_id ,`brand`.name AS brand, `model`.name AS model, `car_class`.name AS car_class, seats, doors, air_conditioning, automatic_gearbox, rental_value_for_day, fuel_consumption, engine_power, color, year_of_issue\n" +
            "FROM `car` \n" +
            "JOIN `model`\n" +
            "ON `car`.model_id = `model`.model_id\n" +
            "JOIN `brand`\n" +
            "ON `model`.brand_id = `brand`.brand_id\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.car_class_id = `car`.car_class_id";

    private static final String FIND_ALL_FREE_CARS = "SELECT `car`.`car_id` ,`brand`.`name` AS `brand`, `model`.`name` AS `model`, `car_class`.`name` AS `car_class`, `seats`, `doors`, `air_conditioning`, `automatic_gearbox`, `rental_value_for_day`, `fuel_consumption`, `engine_power`, `color`, `year_of_issue`\n" +
            "FROM `car`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "WHERE `car_id` NOT IN(SELECT `car`.`car_id`\n" +
            "FROM `car` \n" +
            "JOIN `order`\n" +
            "ON `order`.`car_id` = `car`.`car_id`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "WHERE (? BETWEEN DATE_SUB(`date_received`, INTERVAL 2 HOUR) AND DATE_ADD(`return_date`, INTERVAL 2 HOUR)) OR\n" +
            "(`date_received` BETWEEN DATE_SUB(?, INTERVAL 2 HOUR) AND DATE_ADD(?, INTERVAL 2 HOUR))\n" +
            "GROUP BY `car_id`)";

    private static final String FIND_CAR_BY_ID = "SELECT `car`.`car_id` ,`brand`.`name` AS `brand`, `model`.`name` AS `model`, `car_class`.`name` AS `car_class`, `seats`, `doors`, `air_conditioning`, `automatic_gearbox`, `rental_value_for_day`, `fuel_consumption`, `engine_power`, `color`, `year_of_issue`\n" +
            "FROM `car`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "WHERE `car_id` = ?";

    private static final String TABLE_CAR_FIELD_ID = "car_id";
    private static final String TABLE_CAR_FIELD_BRAND = "brand";
    private static final String TABLE_CAR_FIELD_MODEL = "model";
    private static final String TABLE_CAR_FIELD_CAR_CLASS = "car_class";
    private static final String TABLE_CAR_FIELD_SEATS= "seats";
    private static final String TABLE_CAR_FIELD_DOORS = "doors";
    private static final String TABLE_CAR_FIELD_AIR_CONDITIONING = "air_conditioning";
    private static final String TABLE_CAR_FIELD_AUTOMATIC_GEARBOX = "automatic_gearbox";
    private static final String TABLE_CAR_FIELD_RENTAL_4_DAY = "rental_value_for_day";
    private static final String TABLE_CAR_FIELD_FUEL_CONSUMPTION = "fuel_consumption";
    private static final String TABLE_CAR_FIELD_ENGINE_POWER = "engine_power";
    private static final String TABLE_CAR_FIELD_COLOR = "color";
    private static final String TABLE_CAR_FIELD_YEAR_OF_ISSUE = "year_of_issue";

    @Override
    public List<Car> findAll() {
        List<Car> carList = new LinkedList<>();
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CARS)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                car = createCar(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public List<Car> findAllFreeCars(Timestamp dateReceived, Timestamp returnDate){
        List<Car> carList = new LinkedList<>();
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FREE_CARS)){
            preparedStatement.setTimestamp(1, dateReceived);
            preparedStatement.setTimestamp(2, dateReceived);
            preparedStatement.setTimestamp(3, returnDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                car = createCar(resultSet);
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public Car getCarById(CarDTO carDTO){
        Car car = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_CAR_BY_ID)){
            preparedStatement.setInt(1, carDTO.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                car = createCar(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    private Car createCar(ResultSet resultSet){
        Car car = null;
        try {
            car = new Car();
            car.setId(resultSet.getInt(TABLE_CAR_FIELD_ID));
            car.setBrand(resultSet.getString(TABLE_CAR_FIELD_BRAND));
            car.setModel(resultSet.getString(TABLE_CAR_FIELD_MODEL));
            car.setCarClass(resultSet.getString(TABLE_CAR_FIELD_CAR_CLASS));
            car.setSeats(resultSet.getByte(TABLE_CAR_FIELD_SEATS));
            car.setDoors(resultSet.getByte(TABLE_CAR_FIELD_DOORS));
            car.setAirConditioning(resultSet.getBoolean(TABLE_CAR_FIELD_AIR_CONDITIONING));
            car.setAutomaticGearbox(resultSet.getBoolean(TABLE_CAR_FIELD_AUTOMATIC_GEARBOX));
            car.setRental4Day(resultSet.getBigDecimal(TABLE_CAR_FIELD_RENTAL_4_DAY));
            car.setFuelConsumption(resultSet.getDouble(TABLE_CAR_FIELD_FUEL_CONSUMPTION));
            car.setEnginePower(resultSet.getShort(TABLE_CAR_FIELD_ENGINE_POWER));
            car.setColor(resultSet.getString(TABLE_CAR_FIELD_COLOR));
            car.setYearOfIssue(resultSet.getShort(TABLE_CAR_FIELD_YEAR_OF_ISSUE));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }
}
