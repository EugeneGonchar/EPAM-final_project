package service;

import dto.OrderDTO;
import entity.Order;

public interface OrderService {

    public Order formingDateAddressOfOrder(OrderDTO orderDTO);

}
