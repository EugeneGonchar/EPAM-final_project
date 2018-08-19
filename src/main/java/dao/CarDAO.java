package dao;

import dao.exception.dao.DAOException;
import domain.dto.PageDTO;
import domain.entity.Car;

import java.sql.Timestamp;
import java.util.List;

public abstract class CarDAO extends AbstractDAO {
    public abstract int getCarsCount() throws DAOException;

    public abstract List<Car> findAll(PageDTO pageDTO) throws DAOException;

    public abstract int getFreeCarsCount(Timestamp dateReceived, Timestamp returnDate) throws DAOException;

    public abstract List<Car> getFreeCars(Timestamp dateReceived, Timestamp returnDate, PageDTO pageDTO) throws DAOException;

    public abstract Car getCarById(int id) throws DAOException;

    public abstract void updateImageByCarId(int id, String fileName) throws DAOException;
}
