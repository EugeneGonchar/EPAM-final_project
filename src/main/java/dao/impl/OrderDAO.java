package dao.impl;

import static dao.util.DBFieldName.*;

import dao.AbstractDAO;
import dao.util.DBFieldName;
import dto.FullOrderDTO;
import entity.Address;
import entity.Car;
import entity.Order;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO extends AbstractDAO {

    private static final String FIND_FULL_ORDERS_BY_USER_ID = "SELECT `user_orders`.`order_id`, `user_orders`.`user_id`, `user_orders`.`car_id`, `user_orders`.`date_received`, `user_orders`.`return_date`, `user_orders`.`pickup_address_id`, `user_orders`.`dropoff_address_id`, `user_orders`.`total_cost`, `car`.`seats`, `car`.`doors`, `car`.`air_conditioning`, `car`.`automatic_gearbox`, `car`.`rental_value_for_day`, `car`.`color`, `car`.`fuel_consumption`, `car`.`engine_power`, `model`.`name` AS `model`, `model`.`year_of_issue`, `brand`.`name` AS `brand`, `car_class`.`name` AS `car_class`, `pickup_address`.`street` AS `pickup_address_street`, `pickup_address`.`building` AS `pickup_address_building`, `dropoff_address`.`street` AS `dropoff_address_street`, `dropoff_address`.`building` AS `dropoff_address_building`\n" +
            "FROM (SELECT * FROM `order` WHERE `user_id` = ?) AS `user_orders`\n" +
            "JOIN `car`\n" +
            "ON `user_orders`.`car_id` = `car`.`car_id`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "JOIN `address` AS `pickup_address`\n" +
            "ON `pickup_address`.`address_id` = `user_orders`.`pickup_address_id`\n" +
            "JOIN `address` AS `dropoff_address`\n" +
            "ON `dropoff_address`.`address_id` = `user_orders`.`dropoff_address_id`\n" +
            "ORDER BY `date_received`, `return_date`";

    @Override
    public List findAll() {
        return null;
    }

    public List<FullOrderDTO> getFullOrdersByUser(User user){
        List<FullOrderDTO> fullOrderDTOList = new LinkedList<>();
        FullOrderDTO fullOrderDTO = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_FULL_ORDERS_BY_USER_ID)){
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                fullOrderDTO = new FullOrderDTO();
                fullOrderDTO.setOrder(createOrder(resultSet));
                fullOrderDTO.setCar(createCar(resultSet));
                fullOrderDTO.setPickupAddress(createPickupAddress(resultSet));
                fullOrderDTO.setDropoffAddress(createDropoffAddress(resultSet));
                fullOrderDTOList.add(fullOrderDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fullOrderDTOList;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException{
        Order order = new Order();

        order.setId(resultSet.getInt(TABLE_ORDER_FIELD_ID));
        order.setUserId(resultSet.getInt(TABLE_ORDER_FIELD_USER_ID));
        order.setCarId(resultSet.getInt(TABLE_ORDER_FIELD_CAR_ID));
        order.setDateReceived(resultSet.getTimestamp(TABLE_ORDER_FIELD_DATE_RECEIVED));
        order.setReturnDate(resultSet.getTimestamp(TABLE_ORDER_FIELD_RETURN_DATE));
        order.setPickupAddressId(resultSet.getInt(TABLE_ORDER_FIELD_PICKUP_ADDRESS_ID));
        order.setDropoffAddressId(resultSet.getInt(TABLE_ORDER_FIELD_DROPOFF_ADDRESS_ID));
        order.setTotalCost(resultSet.getBigDecimal(TABLE_ORDER_FIELD_TOTAL_COST));
        return order;
    }

    private Car createCar(ResultSet resultSet) throws SQLException{
        Car car = new Car();

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
        return car;
    }

    private Address createPickupAddress(ResultSet resultSet) throws SQLException{
        Address address = new Address();

        address.setId(resultSet.getInt(DBFieldName.TABLE_ORDER_FIELD_PICKUP_ADDRESS_ID));
        address.setStreet(resultSet.getString(DBFieldName.FIELD_PICKUP_ADDRESS_STREET));
        address.setBuilding(resultSet.getString(DBFieldName.FIELD_PICKUP_ADDRESS_BUILDING));
        return address;
    }

    private Address createDropoffAddress(ResultSet resultSet) throws SQLException{
        Address address = new Address();

        address.setId(resultSet.getInt(DBFieldName.TABLE_ORDER_FIELD_DROPOFF_ADDRESS_ID));
        address.setStreet(resultSet.getString(DBFieldName.FIELD_DROPOFF_ADDRESS_STREET));
        address.setBuilding(resultSet.getString(DBFieldName.FIELD_DROPOFF_ADDRESS_BUILDING));
        return address;
    }
}
