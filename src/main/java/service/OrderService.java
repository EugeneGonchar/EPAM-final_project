package service;

import dto.FullOrderDTO;
import entity.User;

import java.util.List;

public interface OrderService {

    public List<FullOrderDTO> getFullUserOrders(User user);

}
