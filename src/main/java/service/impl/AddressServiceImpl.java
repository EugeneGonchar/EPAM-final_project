package service.impl;

import dao.AddressDAO;
import dao.Transaction;
import dao.exception.dao.DAOException;
import dao.factory.DAOFactory;
import domain.entity.Address;
import service.AddressService;
import service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class  AddressServiceImpl implements AddressService {

    private static final String SPACE = " ";

    @Override
    public List<Address> getAddressList() throws ServiceException {
        AddressDAO addressDAO = DAOFactory.getInstance().getAddressDAO();
        Transaction transaction = new Transaction();
        List<Address> addressList = null;

        transaction.beginTransaction(addressDAO);

        try {
            addressList = addressDAO.getAll();
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving list of addresses", e);
        } finally {
            transaction.endTransaction();
        }
        return addressList;
    }

    @Override
    public Address formingAddressFromString(String stringAddress) throws ServiceException{
        Address address = null;

        AddressDAO addressDAO = DAOFactory.getInstance().getAddressDAO();
        Transaction transaction = new Transaction();

        int index = stringAddress.lastIndexOf(SPACE);
        String building = stringAddress.substring(index+1);
        String street = stringAddress.substring(0, index);

        transaction.beginTransaction(addressDAO);

        try {
            address = addressDAO.getAddressByStreetBuilding(street, building);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving", e);/*убрать*/
        } finally {
            transaction.endTransaction();
        }

        return address;
    }
}
