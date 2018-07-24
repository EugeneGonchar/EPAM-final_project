package service.impl;

import dao.Transaction;
import dao.impl.AddressDAO;
import entity.Address;
import service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    @Override
    public List<Address> getAddressList(){
        AddressDAO addressDAO = new AddressDAO();
        Transaction transaction = new Transaction();
        List<Address> addressList = null;

        transaction.beginTransaction(addressDAO);

        addressList = addressDAO.findAll();

        transaction.commit();
        transaction.endTransaction();
        return addressList;
    }
}
