package dao;

import dao.exception.dao.DAOException;
import dao.util.DBFieldName;
import domain.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {

    protected Connection connection;

    public abstract List<T> getAll() throws DAOException;

    protected int getElementsCount(String query){
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
    }

    protected void updateImageNameById(String query, String fileName, int id){
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, fileName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setConnection(Connection connection){
        this.connection = connection;
    }
}
