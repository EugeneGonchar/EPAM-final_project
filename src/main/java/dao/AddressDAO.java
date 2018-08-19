package dao;

import dao.exception.dao.DAOException;
import domain.entity.Address;

import java.util.List;

public abstract class AddressDAO extends AbstractDAO {
    public abstract List<Address> getAll() throws DAOException;

    public abstract Address getAddressByStreetBuilding(String street, String building) throws DAOException;
}
