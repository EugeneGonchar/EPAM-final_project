package dao;

import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import domain.entity.Order;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO {
    public abstract void insertOrder(Order order);

    public abstract int getFullOrdersCountByUserId(int id);

    public abstract List<FullOrderDTO> getFullOrdersByUser(int id, PageDTO pageDTO);

    public abstract int getFullOrdersCount();

    public abstract List<FullUserOrderDTO> getFullOrders(PageDTO pageDTO);

    public abstract void updateOrder(int orderId, String status);

    public abstract void updateOrder(int orderId, String status, String description);
}
