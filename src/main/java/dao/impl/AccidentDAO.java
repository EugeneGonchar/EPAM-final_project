package dao.impl;

import dao.AbstractDAO;
import dao.util.DBFieldName;
import dao.util.QueryBuilder;
import pojo.dto.PageDTO;
import pojo.entity.Accident;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccidentDAO extends AbstractDAO {

    private static final String GET_ACCIDENTS_COUNT = "SELECT COUNT(*) AS `count` FROM `accident`";

    private static final String FIND_ALL_ACCIDENTS = "SELECT `accident_id`, `description`, `material_damage`, `date`, `order_id`\n" +
            "FROM `accident`";

    public int getAccidentsCount(){
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCIDENTS_COUNT)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt(DBFieldName.FIELD_COUNT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List findAll(PageDTO pageDTO) {
        List<Accident> accidentList = new LinkedList<>();
        Accident accident = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryBuilder.setQueryLimit(FIND_ALL_ACCIDENTS, pageDTO))){
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
    public List findAll() {
        return null;
    }

    private Accident createAccident(ResultSet resultSet) throws SQLException{
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
