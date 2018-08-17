package service;

import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import domain.entity.Order;
import domain.entity.User;

import java.util.List;

public interface OrderService {

    List<FullOrderDTO> getFullUserOrders(User user, PageDTO pageDTO);

    void insertOrder(Order order);

    User insertOrder(Order order, User user);

    List<FullUserOrderDTO> getOrdersList(PageDTO pageDTO);

    void updateOrderStatus(int orderId, String status);

    void updateOrderStatus(int orderId, String status, String description);
}
