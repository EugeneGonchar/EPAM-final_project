package dao;

import dao.exception.dao.DAOException;
import domain.dto.PageDTO;
import domain.entity.Accident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AccidentDAO extends AbstractDAO{
    public abstract int getAccidentsCount() throws DAOException;

    public abstract List<Accident> getAll(PageDTO pageDTO) throws DAOException;
}
