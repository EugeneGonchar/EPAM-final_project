package dao.util;

import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.UserRoleDTO;
import domain.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.util.DBFieldName.*;
import static dao.util.DBFieldName.TABLE_CAR_FIELD_IMAGE;

public class DomainCreator {

    public static Accident createAccident(ResultSet resultSet) throws SQLException {
        Accident accident = null;

        accident = new Accident();
        accident.setId(resultSet.getInt(DBFieldName.TABLE_ACCIDENT_FIELD_ID));
        accident.setDate(resultSet.getDate(DBFieldName.TABLE_ACCIDENT_FIELD_DATE));
        accident.setDescription(resultSet.getString(DBFieldName.TABLE_ACCIDENT_FIELD_DESCRIPTION));
        accident.setMaterialDamage(resultSet.getBigDecimal(DBFieldName.TABLE_ACCIDENT_FIELD_MATERIAL_DAMAGE));
        accident.setOrderId(resultSet.getInt(DBFieldName.TABLE_ACCIDENT_FIELD_ORDER_ID));
        return accident;
    }

    public static Car createCar(ResultSet resultSet) throws SQLException{
        Car car = null;

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
        car.setEngineType(resultSet.getString(TABLE_CAR_FIELD_ENGINE_TYPE));
        car.setColor(resultSet.getString(TABLE_CAR_FIELD_COLOR));
        car.setYearOfIssue(resultSet.getShort(TABLE_CAR_FIELD_YEAR_OF_ISSUE));
        car.setImage(resultSet.getString(TABLE_CAR_FIELD_IMAGE));
        return car;
    }

    public static User createFullUser(ResultSet resultSet) throws SQLException{
        User user = new User();

        user.setId(resultSet.getInt(TABLE_USER_FIELD_ID));
        user.setLogin(resultSet.getString(TABLE_USER_FIELD_LOGIN));
        user.setPassword(resultSet.getString(TABLE_USER_FIELD_PASSWORD));
        user.setEmail(resultSet.getString(TABLE_USER_FIELD_EMAIL));
        user.setPhone(resultSet.getString(TABLE_USER_FIELD_PHONE));
        user.setFirstName(resultSet.getString(TABLE_USER_FIELD_FIRST_NAME));
        user.setLastName(resultSet.getString(TABLE_USER_FIELD_LAST_NAME));
        user.setRoleId(resultSet.getInt(TABLE_USER_FIELD_ROLE_ID));
        user.setProfileImage(resultSet.getString(TABLE_USER_FIELD_PROFILE_IMAGE));
        return user;
    }

    public static UserRoleDTO createUserRoleDTO(ResultSet resultSet) throws SQLException{
        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId(resultSet.getInt(TABLE_USER_FIELD_ID));
        userRoleDTO.setFirstName(resultSet.getString(TABLE_USER_FIELD_FIRST_NAME));
        userRoleDTO.setLastName(resultSet.getString(TABLE_USER_FIELD_LAST_NAME));
        userRoleDTO.setLogin(resultSet.getString(TABLE_USER_FIELD_LOGIN));
        userRoleDTO.setEmail(resultSet.getString(TABLE_USER_FIELD_EMAIL));
        userRoleDTO.setPhone(resultSet.getString(TABLE_USER_FIELD_PHONE));
        userRoleDTO.setRoleId(resultSet.getInt(TABLE_USER_FIELD_ROLE_ID));
        userRoleDTO.setRole(resultSet.getString(FIELD_ROLE));
        return userRoleDTO;
    }

    public static Address createAddress(ResultSet resultSet) throws SQLException{
        Address address = new Address();

        address.setId(resultSet.getInt(TABLE_ADDRESS_FIELD_ID));
        address.setStreet(resultSet.getString(TABLE_ADDRESS_FIELD_STREET));
        address.setBuilding(resultSet.getString(TABLE_ADDRESS_FIELD_BUILDING));
        return address;
    }

    public static FullOrderDTO createFullOrderDTO(ResultSet resultSet) throws SQLException{
        FullOrderDTO fullOrderDTO = new FullOrderDTO();

        fullOrderDTO.setOrder(DomainCreator.createOrder(resultSet));
        fullOrderDTO.setCar(DomainCreator.createCar(resultSet));
        fullOrderDTO.setPickupAddress(DomainCreator.createPickupAddress(resultSet));
        fullOrderDTO.setDropoffAddress(DomainCreator.createDropoffAddress(resultSet));
        fullOrderDTO.setOrderStatus(DomainCreator.createOrderStatus(resultSet));
        return fullOrderDTO;
    }

    public static FullUserOrderDTO createFullUserOrderDTO(ResultSet resultSet) throws SQLException{
        FullUserOrderDTO fullUserOrderDTO = new FullUserOrderDTO();

        fullUserOrderDTO.setOrder(DomainCreator.createOrder(resultSet));
        fullUserOrderDTO.setCar(DomainCreator.createCar(resultSet));
        fullUserOrderDTO.setPickupAddress(DomainCreator.createPickupAddress(resultSet));
        fullUserOrderDTO.setDropoffAddress(DomainCreator.createDropoffAddress(resultSet));
        fullUserOrderDTO.setOrderStatus(DomainCreator.createOrderStatus(resultSet));
        fullUserOrderDTO.setUser(DomainCreator.createUser(resultSet));
        return fullUserOrderDTO;
    }

    public static Role createRole(ResultSet resultSet) throws SQLException{
        Role role = new Role();

        role.setId(resultSet.getInt(TABLE_ROLE_FIELD_ROLE_ID));
        role.setName(resultSet.getString(TABLE_ROLE_FIELD_NAME));
        return role;
    }

    private static Order createOrder(ResultSet resultSet) throws SQLException{
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

    private static Address createPickupAddress(ResultSet resultSet) throws SQLException{
        Address address = new Address();

        address.setId(resultSet.getInt(DBFieldName.TABLE_ORDER_FIELD_PICKUP_ADDRESS_ID));
        address.setStreet(resultSet.getString(DBFieldName.FIELD_PICKUP_ADDRESS_STREET));
        address.setBuilding(resultSet.getString(DBFieldName.FIELD_PICKUP_ADDRESS_BUILDING));
        return address;
    }

    private static Address createDropoffAddress(ResultSet resultSet) throws SQLException{
        Address address = new Address();

        address.setId(resultSet.getInt(DBFieldName.TABLE_ORDER_FIELD_DROPOFF_ADDRESS_ID));
        address.setStreet(resultSet.getString(DBFieldName.FIELD_DROPOFF_ADDRESS_STREET));
        address.setBuilding(resultSet.getString(DBFieldName.FIELD_DROPOFF_ADDRESS_BUILDING));
        return address;
    }

    private static OrderStatus createOrderStatus(ResultSet resultSet) throws SQLException{
        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setId(resultSet.getInt(DBFieldName.TABLE_ORDER_STATUS_FIELD_ID));
        orderStatus.setStatus(resultSet.getString(DBFieldName.TABLE_ORDER_STATUS_FIELD_STATUS));
        return orderStatus;
    }

    private static User createUser(ResultSet resultSet) throws SQLException{
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
