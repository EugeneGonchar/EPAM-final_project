package dao;

import pojo.entity.Address;

import java.util.List;

public abstract class AddressDAO extends AbstractDAO {
    public abstract List<Address> getAll();

    public abstract Address getAddressByStreetBuilding(String street, String building);
}
