package dao.impl;

import dao.AccidentDAO;
import dao.exception.dao.DAOException;
import dao.util.DomainCreator;
import dao.util.QueryBuilder;
import domain.dto.PageDTO;
import domain.entity.Accident;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccidentDAOImpl extends AccidentDAO {

    private static final String SELECT_ACCIDENTS_COUNT = "SELECT COUNT(*) AS `count` FROM `accident`";

    private static final String SELECT_ACCIDENTS = "SELECT `accident_id`, `description`, `material_damage`, `date`, `order_id`\n" +
            "FROM `accident`";

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public int getAccidentsCount(){
        return getElementsCount(SELECT_ACCIDENTS_COUNT);
    }

    @Override
    public List<Accident> getAll(PageDTO pageDTO) throws DAOException{
        List<Accident> accidentList = new LinkedList<>();
        Accident accident = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_ACCIDENTS, pageDTO))){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                accident = DomainCreator.createAccident(resultSet);
                accidentList.add(accident);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception throws during retrieving pageDto list", e);
        }
        return accidentList;
    }
}
