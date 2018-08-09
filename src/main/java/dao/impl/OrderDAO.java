package dao.impl;

import static dao.util.DBFieldName.*;

import dao.AbstractDAO;
import dao.util.DBFieldName;
import dto.FullOrderDTO;
import dto.FullUserOrderDTO;
import entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO extends AbstractDAO {

    private static final String FIND_FULL_ORDERS_BY_USER_ID = "SELECT `user_orders`.`order_id`, `user_orders`.`user_id`, `user_orders`.`car_id`, `user_orders`.`date_received`, `user_orders`.`return_date`, `user_orders`.`pickup_address_id`, `user_orders`.`dropoff_address_id`, `user_orders`.`total_cost`, `car`.`seats`, `car`.`doors`, `car`.`air_conditioning`, `car`.`automatic_gearbox`, `car`.`rental_value_for_day`, `car`.`color`, `car`.`fuel_consumption`, `engine`.`type` AS `engine_type`, `model`.`name` AS `model`, `model`.`year_of_issue`, `brand`.`name` AS `brand`, `car_class`.`name` AS `car_class`, `pickup_address`.`street` AS `pickup_address_street`, `pickup_address`.`building` AS `pickup_address_building`, `dropoff_address`.`street` AS `dropoff_address_street`, `dropoff_address`.`building` AS `dropoff_address_building`, `order_status`.`status`, `user_orders`.`status_id`\n" +
            "FROM (SELECT * FROM `order` WHERE `user_id` = ?) AS `user_orders`\n" +
            "JOIN `car`\n" +
            "ON `user_orders`.`car_id` = `car`.`car_id`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "JOIN `engine`\n"+
            "ON `engine`.`engine_id` = `car`.`engine_id`\n"+
            "JOIN `address` AS `pickup_address`\n" +
            "ON `pickup_address`.`address_id` = `user_orders`.`pickup_address_id`\n" +
            "JOIN `address` AS `dropoff_address`\n" +
            "ON `dropoff_address`.`address_id` = `user_orders`.`dropoff_address_id`\n" +
            "JOIN `order_status`\n" +
            "ON `order_status`.`status_id` = `user_orders`.`status_id`\n" +
            "ORDER BY `date_received`, `return_date`";

    private static final String FIND_FULL_ORDERS_WITH_USERS = "SELECT `order`.`order_id`, `order`.`user_id`, `order`.`car_id`, `order`.`date_received`, `order`.`return_date`, `order`.`pickup_address_id`, `order`.`dropoff_address_id`, `order`.`total_cost`, `car`.`seats`, `car`.`doors`, `car`.`air_conditioning`, `car`.`automatic_gearbox`, `car`.`rental_value_for_day`, `car`.`color`, `car`.`fuel_consumption`, `engine`.`type` AS `engine_type`, `model`.`name` AS `model`, `model`.`year_of_issue`, `brand`.`name` AS `brand`, `car_class`.`name` AS `car_class`, `pickup_address`.`street` AS `pickup_address_street`, `pickup_address`.`building` AS `pickup_address_building`, `dropoff_address`.`street` AS `dropoff_address_street`, `dropoff_address`.`building` AS `dropoff_address_building`, `order_status`.`status`, `order`.`status_id`, `user`.`user_id`, `user`.`first_name`, `user`.`last_name`, `user`.`phone`, `user`.`login`, `user`.`email`, `user`.`role_id`\n" +
            "FROM `order`\n" +
            "JOIN `user`\n" +
            "ON `order`.`user_id` = `user`.`user_id`" +
            "JOIN `car`\n" +
            "ON `order`.`car_id` = `car`.`car_id`\n" +
            "JOIN `model`\n" +
            "ON `car`.`model_id` = `model`.`model_id`\n" +
            "JOIN `brand`\n" +
            "ON `model`.`brand_id` = `brand`.`brand_id`\n" +
            "JOIN `car_class`\n" +
            "ON `car_class`.`car_class_id` = `car`.`car_class_id`\n" +
            "JOIN `engine`\n"+
            "ON `engine`.`engine_id` = `car`.`engine_id`\n"+
            "JOIN `address` AS `pickup_address`\n" +
            "ON `pickup_address`.`address_id` = `order`.`pickup_address_id`\n" +
            "JOIN `address` AS `dropoff_address`\n" +
            "ON `dropoff_address`.`address_id` = `order`.`dropoff_address_id`\n" +
            "JOIN `order_status`\n" +
            "ON `order_status`.`status_id` = `order`.`status_id`\n" +
            "ORDER BY `date_received`, `return_date`";

    private static final String INSERT_ORDER = "INSERT INTO `order` (`user_id`, `car_id`, `date_received`, `return_date`, `pickup_address_id`, `dropoff_address_id`, `total_cost`)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                fullOrderDTO.setOrderStatus(createOrderStatus(resultSet));
                fullOrderDTOList.add(fullOrderDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fullOrderDTOList;
    }

    public List<FullUserOrderDTO> getFullOrders(){
        List<FullUserOrderDTO> fullUserOrderDTOList = new LinkedList<>();
        FullUserOrderDTO fullUserOrderDTO = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_FULL_ORDERS_WITH_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                fullUserOrderDTO = new FullUserOrderDTO();
                fullUserOrderDTO.setOrder(createOrder(resultSet));
                fullUserOrderDTO.setCar(createCar(resultSet));
                fullUserOrderDTO.setPickupAddress(createPickupAddress(resultSet));
                fullUserOrderDTO.setDropoffAddress(createDropoffAddress(resultSet));
                fullUserOrderDTO.setOrderStatus(createOrderStatus(resultSet));
                fullUserOrderDTO.setUser(createUser(resultSet));
                fullUserOrderDTOList.add(fullUserOrderDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fullUserOrderDTOList;
    }

    public void insertOrder(Order order){
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)){
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getCarId());
            preparedStatement.setTimestamp(3, order.getDateReceived());
            preparedStatement.setTimestamp(4, order.getReturnDate());
            preparedStatement.setInt(5, order.getPickupAddressId());
            preparedStatement.setInt(6, order.getDropoffAddressId());
            preparedStatement.setBigDecimal(7, order.getTotalCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
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
        order.setStatusId(resultSet.getInt(TABLE_ORDER_FIELD_STATUS_ID));
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
        car.setEngineType(resultSet.getString(TABLE_CAR_FIELD_ENGINE_TYPE));
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

    private OrderStatus createOrderStatus(ResultSet resultSet) throws SQLException{
        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setId(resultSet.getInt(DBFieldName.TABLE_ORDER_STATUS_FIELD_ID));
        orderStatus.setStatus(resultSet.getString(DBFieldName.TABLE_ORDER_STATUS_FIELD_STATUS));
        return orderStatus;
    }

    private User createUser(ResultSet resultSet) throws SQLException{
        User user = new User();

        user.setId(resultSet.getInt(TABLE_USER_FIELD_ID));
        user.setLogin(resultSet.getString(TABLE_USER_FIELD_LOGIN));
        user.setFirstName(resultSet.getString(TABLE_USER_FIELD_FIRST_NAME));
        user.setLastName(resultSet.getString(TABLE_USER_FIELD_LAST_NAME));
        user.setEmail(resultSet.getString(TABLE_USER_FIELD_EMAIL));
        user.setPhone(resultSet.getString(TABLE_USER_FIELD_PHONE));
        user.setRoleId(resultSet.getInt(TABLE_USER_FIELD_ROLE_ID));
        return user;
    }
}
