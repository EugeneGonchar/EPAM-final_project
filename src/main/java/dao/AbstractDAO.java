package dao;

import dao.util.DBFieldName;
import pojo.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {

    protected Connection connection;

    public abstract List<T> findAll();

    /*public abstract int getCount();*/

    /*protected int getElementsCount(String query){
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt(DBFieldName.FIELD_COUNT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }*/

    void setConnection(Connection connection){
        this.connection = connection;
    }
}
