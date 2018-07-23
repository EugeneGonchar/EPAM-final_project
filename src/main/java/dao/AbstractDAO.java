package dao;

import java.sql.Connection;

public abstract class AbstractDAO {

    Connection connection;

    void setConnection(Connection connection){
        this.connection = connection;
    }
}
