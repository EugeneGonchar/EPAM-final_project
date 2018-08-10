package service.impl;

import dao.Transaction;
import dao.impl.OrderDAO;
import dao.impl.UserDAO;
import pojo.dto.FullOrderDTO;
import pojo.dto.FullUserOrderDTO;
import pojo.dto.PageDTO;
import pojo.entity.Car;
import pojo.entity.Order;
import pojo.entity.User;
import service.OrderService;
import service.util.Hash;
import service.util.PasswordCreator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<FullOrderDTO> getFullUserOrders(User user, PageDTO pageDTO){
        OrderDAO orderDAO = new OrderDAO();
        Transaction transaction = new Transaction();
        List<FullOrderDTO> fullOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        pageDTO.setElementsCount(orderDAO.getOrdersCountByUserId(user));
        pageDTO.calculatePagesCount();
        fullOrderDTOList = orderDAO.getFullOrdersByUser(user, pageDTO);

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

    @Override
    public User insertOrder(Order order, User user){
        OrderDAO orderDAO = new OrderDAO();
        UserDAO userDAO = new UserDAO();
        Transaction transaction = new Transaction();
        User registeredUser = null;

        user.setLogin(user.getEmail());
        String generatedPassword = PasswordCreator.createPassword();
        user.setPassword(Hash.getCryptoSha256(generatedPassword));

        transaction.beginTransaction(orderDAO, userDAO);

        userDAO.insertUser(user);
        registeredUser = userDAO.getUserByLogin(user.getLogin());
        order.setUserId(registeredUser.getId());
        orderDAO.insertOrder(order);

        try{
            transaction.commit();
        } catch (SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();

        registeredUser.setPassword(generatedPassword);

        return registeredUser;
    }

    public static BigDecimal getCalculatedTotalCost(Car car, int rentDays){
        return car.getRental4Day().multiply(new BigDecimal(rentDays));
    }
}
