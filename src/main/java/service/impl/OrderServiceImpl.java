package service.impl;

import dao.Transaction;
import dao.impl.OrderDAO;
import dto.FullOrderDTO;
import entity.User;
import service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<FullOrderDTO> getFullUserOrders(User user){
        OrderDAO orderDAO = new OrderDAO();
        Transaction transaction = new Transaction();
        List<FullOrderDTO> fullOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        fullOrderDTOList = orderDAO.getFullOrdersByUser(user);

        try{
            transaction.commit();
        } catch (SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();

        return fullOrderDTOList;
    }

}
