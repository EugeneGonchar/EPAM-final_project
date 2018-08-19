package dao;

import dao.exception.dao.DAOException;
import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import domain.entity.Order;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO {
    public abstract void insertOrder(Order order) throws DAOException;

    public abstract int getFullOrdersCountByUserId(int id) throws DAOException;

    public abstract List<FullOrderDTO> getFullOrdersById(int id, PageDTO pageDTO) throws DAOException;

    public abstract int getFullOrdersCount() throws DAOException;

    public abstract List<FullUserOrderDTO> getFullOrders(PageDTO pageDTO) throws DAOException;

    public abstract void updateOrder(int orderId, String status) throws DAOException;

    public abstract void updateOrder(int orderId, String status, String description) throws DAOException;
}
