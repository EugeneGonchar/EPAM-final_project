package service;

import domain.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressList();
    Address formingAddressFromString(String stringAddress);

}
