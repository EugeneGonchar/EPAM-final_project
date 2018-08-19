package service;

import domain.entity.Address;
import service.exception.ServiceException;

import java.util.List;

public interface AddressService {

    List<Address> getAddressList() throws ServiceException;
    Address formingAddressFromString(String stringAddress) throws ServiceException;

}
