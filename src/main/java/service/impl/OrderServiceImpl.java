package service.impl;

import dao.Transaction;
import dao.impl.AddressDAO;
import dto.OrderDTO;
import entity.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order formingDateAddressOfOrder(OrderDTO orderDTO){
        Order order = new Order();

        String[] pickupAddressData = orderDTO.getPickupAddress().split(" ");
        String[] dropoffAddressData = orderDTO.getPickupAddress().split(" ");

        AddressDAO addressDAO = new AddressDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(addressDAO);

        order.setPickupAddressId(addressDAO.getAddressIdByStreetBuilding(pickupAddressData[0], pickupAddressData[1]));
        order.setDropoffAddressId(addressDAO.getAddressIdByStreetBuilding(dropoffAddressData[0], dropoffAddressData[1]));

        transaction.commit();
        transaction.endTransaction();

        order.setDateReceived(orderDTO.getDateReceived());
        order.setReturnDate(orderDTO.getDateReceived());

        return order;
    }

}
