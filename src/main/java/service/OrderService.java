package service;

import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.OrderDatesDTO;
import domain.dto.PageDTO;
import domain.entity.Order;
import domain.entity.User;
import service.exception.DateInvalidException;
import service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    List<FullOrderDTO> getFullUserOrders(User user, PageDTO pageDTO) throws ServiceException;

    void formingOrder(OrderDatesDTO orderDatesDTO) throws DateInvalidException;

    void insertOrder(Order order) throws ServiceException;

    User insertOrder(Order order, User user) throws ServiceException;

    List<FullUserOrderDTO> getOrdersList(PageDTO pageDTO) throws ServiceException;

    void updateOrderStatus(int orderId, String status) throws ServiceException;

    void updateOrderStatus(int orderId, String status, String description) throws ServiceException;
}
