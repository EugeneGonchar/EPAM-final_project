package dao;

import pojo.dto.PageDTO;
import pojo.entity.Accident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AccidentDAO extends AbstractDAO{
    public abstract int getAccidentsCount();

    public abstract List<Accident> getAll(PageDTO pageDTO);

    public abstract Accident createAccident(ResultSet resultSet) throws SQLException;
}
