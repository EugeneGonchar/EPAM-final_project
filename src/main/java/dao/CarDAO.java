package dao;

import domain.dto.PageDTO;
import domain.entity.Car;

import java.sql.Timestamp;
import java.util.List;

public abstract class CarDAO extends AbstractDAO {
    public abstract int getCarsCount();

    public abstract List<Car> findAll(PageDTO pageDTO);

    public abstract int getFreeCarsCount(Timestamp dateReceived, Timestamp returnDate);

    public abstract List<Car> getFreeCars(Timestamp dateReceived, Timestamp returnDate, PageDTO pageDTO);

    public abstract Car getCarById(int id);

    public abstract void updateImageByCarId(int id, String fileName);
}
