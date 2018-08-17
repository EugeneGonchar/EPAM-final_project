package dao.impl;

import dao.AbstractDAO;
import dao.AccidentDAO;
import dao.util.DBFieldName;
import dao.util.QueryBuilder;
import pojo.dto.PageDTO;
import pojo.entity.Accident;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccidentDAOImpl extends AccidentDAO {

    private static final String SELECT_ACCIDENTS_COUNT = "SELECT COUNT(*) AS `count` FROM `accident`";

    private static final String SELECT_ALL_ACCIDENTS = "SELECT `accident_id`, `description`, `material_damage`, `date`, `order_id`\n" +
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
    public List getAll(PageDTO pageDTO) {
        List<Accident> accidentList = new LinkedList<>();
        Accident accident = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(SELECT_ALL_ACCIDENTS, pageDTO))){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                accident = createAccident(resultSet);
                accidentList.add(accident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accidentList;
    }

    @Override
    public Accident createAccident(ResultSet resultSet) throws SQLException{
        Accident accident = null;

        accident = new Accident();
        accident.setId(resultSet.getInt(DBFieldName.TABLE_ACCIDENT_FIELD_ID));
        accident.setDate(resultSet.getDate(DBFieldName.TABLE_ACCIDENT_FIELD_DATE));
        accident.setDescription(resultSet.getString(DBFieldName.TABLE_ACCIDENT_FIELD_DESCRIPTION));
        accident.setMaterialDamage(resultSet.getBigDecimal(DBFieldName.TABLE_ACCIDENT_FIELD_MATERIAL_DAMAGE));
        accident.setOrderId(resultSet.getInt(DBFieldName.TABLE_ACCIDENT_FIELD_ORDER_ID));
        return accident;
    }
}
