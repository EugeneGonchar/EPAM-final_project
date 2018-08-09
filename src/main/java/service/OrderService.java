package service;

import dto.FullOrderDTO;
import dto.FullUserOrderDTO;
import entity.Order;
import entity.User;

import java.util.List;

public interface OrderService {

    public List<FullOrderDTO> getFullUserOrders(User user);

    public void insertOrder(Order order);

    public List<FullUserOrderDTO> getFullOrders();

}
