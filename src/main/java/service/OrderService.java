package service;

import pojo.dto.FullOrderDTO;
import pojo.dto.FullUserOrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Order;
import pojo.entity.User;

import java.util.List;

public interface OrderService {

    List<FullOrderDTO> getFullUserOrders(User user, PageDTO pageDTO);

    void insertOrder(Order order);

    User insertOrder(Order order, User user);

    List<FullUserOrderDTO> getOrdersList(PageDTO pageDTO);

}
