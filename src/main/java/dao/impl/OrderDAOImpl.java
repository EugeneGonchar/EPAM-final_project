package dao.impl;

import dao.OrderDAO;
import dao.exception.dao.DAOException;
import dao.util.DBFieldName;
import dao.util.DomainCreator;
import dao.util.QueryBuilder;
import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import domain.entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDAOImpl extends OrderDAO {

    private static final String INSERT_ORDER = "INSERT INTO `order` (`user_id`, `car_id`, `date_received`, `return_date`, `pickup_address_id`, `dropoff_address_id`, `total_cost`)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ORDERS_COUNT = "SELECT COUNT(*) AS `count` FROM `order`";

    private static final String SELECT_ORDERS_COUNT_BY_USER_ID = "SELECT COUNT(*) AS `count`\n" +
            "FROM (SELECT * FROM `order` WHERE `user_id` = ?) AS `user_orders`\n";

    private static final String SELECT_FULL_ORDERS_BY_USER_ID = "SELECT `user_orders`.`order_id`, `user_orders`.`user_id`, `user_orders`.`car_id`, `user_orders`.`date_received`, `user_orders`.`return_date`, `user_orders`.`pickup_address_id`, `user_orders`.`dropoff_address_id`, `user_orders`.`total_cost`, `car`.`seats`, `car`.`image`, `car`.`doors`, `car`.`air_conditioning`, `car`.`automatic_gearbox`, `car`.`rental_value_for_day`, `car`.`color`, `car`.`fuel_consumption`, `engine`.`type` AS `engine_type`, `model`.`name` AS `model`, `model`.`year_of_issue`, `brand`.`name` AS `brand`, `car_class`.`name` AS `car_class`, `pickup_address`.`street` AS `pickup_address_street`, `pickup_address`.`building` AS `pickup_address_building`, `dropoff_address`.`street` AS `dropoff_address_street`, `dropoff_address`.`building` AS `dropoff_address_building`, `order_status`.`status`, `user_orders`.`status_id`\n" +
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

    private static final String SELECT_FULL_ORDERS_WITH_USERS = "SELECT `order`.`order_id`, `order`.`user_id`, `order`.`car_id`, `order`.`date_received`, `order`.`return_date`, `order`.`pickup_address_id`, `order`.`dropoff_address_id`, `order`.`total_cost`, `car`.`seats`, `car`.`image`, `car`.`doors`, `car`.`air_conditioning`, `car`.`automatic_gearbox`, `car`.`rental_value_for_day`, `car`.`color`, `car`.`fuel_consumption`, `engine`.`type` AS `engine_type`, `model`.`name` AS `model`, `model`.`year_of_issue`, `brand`.`name` AS `brand`, `car_class`.`name` AS `car_class`, `pickup_address`.`street` AS `pickup_address_street`, `pickup_address`.`building` AS `pickup_address_building`, `dropoff_address`.`street` AS `dropoff_address_street`, `dropoff_address`.`building` AS `dropoff_address_building`, `order_status`.`status`, `order`.`status_id`, `user`.`user_id`, `user`.`first_name`, `user`.`last_name`, `user`.`phone`, `user`.`login`, `user`.`email`, `user`.`role_id`\n" +
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

    private static final String UPDATE_ORDER_STATUS_WITH_DESCRIPTION = "UPDATE `order` SET `description` = ?, `status_id` = (SELECT `status_id` FROM `order_status` WHERE `status` = ?) WHERE `order_id` = ?";
    private static final String UPDATE_ORDER_STATUS = "UPDATE `order` SET `status_id` = (SELECT `status_id` FROM `order_status` WHERE `status` = ?) WHERE `order_id` = ?";

    @Override
    public void insertOrder(Order order) throws DAOException {
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
            throw new DAOException("Exception throws during inserting order", e);
        }
    }

    @Override
    public List getAll() {
        return null;
    }

    /*@Override
    public int getCount(){
        return getElementsCount(SELECT_ORDERS_COUNT_BY_USER_ID);
    }*/

    @Override
    public int getFullOrdersCountByUserId(int id) throws DAOException{
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_COUNT_BY_USER_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt(DBFieldName.FIELD_COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving count of orders by user id", e);
        }
        return count;
    }

    @Override
    public List<FullOrderDTO> getFullOrdersById(int id, PageDTO pageDTO) throws DAOException{
        List<FullOrderDTO> fullOrderDTOList = new LinkedList<>();
        FullOrderDTO fullOrderDTO = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_FULL_ORDERS_BY_USER_ID, pageDTO))){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                fullOrderDTO = DomainCreator.createFullOrderDTO(resultSet);
                fullOrderDTOList.add(fullOrderDTO);
            }

        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving list of orders by id", e);
        }

        return fullOrderDTOList;
    }

    @Override
    public int getFullOrdersCount() throws DAOException{
        return getElementsCount(SELECT_ORDERS_COUNT);
    }

    @Override
    public List<FullUserOrderDTO> getFullOrders(PageDTO pageDTO) throws DAOException{
        List<FullUserOrderDTO> fullUserOrderDTOList = new LinkedList<>();
        FullUserOrderDTO fullUserOrderDTO = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_FULL_ORDERS_WITH_USERS, pageDTO))){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                fullUserOrderDTO = DomainCreator.createFullUserOrderDTO(resultSet);
                fullUserOrderDTOList.add(fullUserOrderDTO);
            }

        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving list of orders", e);
        }

        return fullUserOrderDTOList;
    }

    @Override
    public void updateOrder(int orderId, String status) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)){
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Exception throws during updating order", e);
        }
    }

    @Override
    public void updateOrder(int orderId, String status, String description) throws DAOException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_WITH_DESCRIPTION)){
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("Exception throws during updating order", e);
        }
    }
}
