package service.impl;

import dao.Transaction;
import dao.impl.OrderDAO;
import dto.FullOrderDTO;
import dto.FullUserOrderDTO;
import entity.Car;
import entity.Order;
import entity.User;
import service.OrderService;

import java.math.BigDecimal;
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

    @Override
    public List<FullUserOrderDTO> getFullOrders(){
        OrderDAO orderDAO = new OrderDAO();
        Transaction transaction = new Transaction();
        List<FullUserOrderDTO> fullUserOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        fullUserOrderDTOList = orderDAO.getFullOrders();

        try{
            transaction.commit();
        } catch (SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();

        return fullUserOrderDTOList;
    }

    @Override
    public void insertOrder(Order order){
        OrderDAO orderDAO = new OrderDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(orderDAO);

        orderDAO.insertOrder(order);

        try{
            transaction.commit();
        } catch (SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();
    }

    public static BigDecimal getCalculatedTotalCost(Car car, int rentDays){
        return car.getRental4Day().multiply(new BigDecimal(rentDays));
    }
}
