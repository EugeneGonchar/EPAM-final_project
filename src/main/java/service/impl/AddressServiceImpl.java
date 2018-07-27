package service.impl;

import dao.Transaction;
import dao.impl.AddressDAO;
import entity.Address;
import service.AddressService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    private static final String SPACE = " ";

    @Override
    public List<Address> getAddressList(){
        AddressDAO addressDAO = new AddressDAO();
        Transaction transaction = new Transaction();
        List<Address> addressList = null;

        transaction.beginTransaction(addressDAO);

        addressList = addressDAO.findAll();

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();
        return addressList;
    }

    @Override
    public Address formingAddressFromString(String stringAddress){
        Address address = null;

        AddressDAO addressDAO = new AddressDAO();
        Transaction transaction = new Transaction();

        String[] stringAddressData = stringAddress.split(SPACE);

        transaction.beginTransaction(addressDAO);

        address = addressDAO.getAddressByStreetBuilding(stringAddressData[0], stringAddressData[1]);

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return address;
    }
}
