package service;

import pojo.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddressList();
    Address formingAddressFromString(String stringAddress);

}
