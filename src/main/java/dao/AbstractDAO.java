package dao;

import entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {

    protected Connection connection;

    public abstract List<T> findAll();

    void setConnection(Connection connection){
        this.connection = connection;
    }
}
