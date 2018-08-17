package dao;

import pojo.dto.FullOrderDTO;
import pojo.dto.FullUserOrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Order;
import pojo.entity.User;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO {
    public abstract void insertOrder(Order order);

    public abstract int getFullOrdersCountByUser(User user);

    public abstract List<FullOrderDTO> getFullOrdersByUser(User user, PageDTO pageDTO);

    public abstract int getFullOrdersCount();

    public abstract List<FullUserOrderDTO> getFullOrders(PageDTO pageDTO);

    public abstract void updateOrder(int orderId, String status);

    public abstract void updateOrder(int orderId, String status, String description);
}
