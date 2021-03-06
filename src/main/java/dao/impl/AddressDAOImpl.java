package dao.impl;

import dao.AddressDAO;
import dao.exception.dao.DAOException;
import dao.util.DomainCreator;
import domain.entity.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDAOImpl extends AddressDAO {

    private static final String SELECT_ADDRESS_COUNT = "SELECT COUNT(*) AS `count` FROM `address`";
    private static final String SELECT_ADDRESSES = "SELECT `address_id`, `street`, `building` FROM `address`";
    private static final String SELECT_ADDRESS_BY_STREET_BUILDING = "SELECT `address_id`, `street`, `building` FROM `address` WHERE street=? AND `building`=?";

    @Override
    public List<Address> getAll() throws DAOException{
        List<Address> addressList = new LinkedList<>();
        Address address = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESSES)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                address = DomainCreator.createAddress(resultSet);
                addressList.add(address);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving address list", e);
        }

        return addressList;
    }

    @Override
    public Address getAddressByStreetBuilding(String street, String building) throws DAOException{
        Address address = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_STREET_BUILDING)){
            preparedStatement.setString(1, street);
            preparedStatement.setString(2, building);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                address = DomainCreator.createAddress(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving address by street and building", e);
        }
        return address;
    }
}
