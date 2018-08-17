package service.impl;

import dao.OrderDAO;
import dao.Transaction;
import dao.UserDAO;
import dao.factory.DAOFactory;
import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import domain.entity.Car;
import domain.entity.Order;
import domain.entity.User;
import service.OrderService;
import service.util.Hash;
import service.util.PasswordCreator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<FullOrderDTO> getFullUserOrders(User user, PageDTO pageDTO){
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();
        List<FullOrderDTO> fullOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        pageDTO.setElementsCount(orderDAO.getFullOrdersCountByUserId(user.getId()));
        pageDTO.calculatePagesCount();
        fullOrderDTOList = orderDAO.getFullOrdersByUser(user.getId(), pageDTO);

        try{
            transaction.commit();
        } catch (SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();

        return fullOrderDTOList;
    }

    @Override
    public List<FullUserOrderDTO> getOrdersList(PageDTO pageDTO){
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();
        List<FullUserOrderDTO> fullUserOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        pageDTO.setElementsCount(orderDAO.getFullOrdersCount());
        pageDTO.calculatePagesCount();
        fullUserOrderDTOList = orderDAO.getFullOrders(pageDTO);

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
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
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
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
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

    public void updateOrderStatus(int orderId, String status){
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(orderDAO);

        orderDAO.updateOrder(orderId, status);

        try{
            transaction.commit();
        } catch(SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();
    }

    public void updateOrderStatus(int orderId, String status, String description){
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(orderDAO);

        orderDAO.updateOrder(orderId, status, description);

        try{
            transaction.commit();
        } catch(SQLException e){
            transaction.rollback();
        }
        transaction.endTransaction();
    }

    public static BigDecimal getCalculatedTotalCost(Car car, int rentDays){
        return car.getRental4Day().multiply(new BigDecimal(rentDays));
    }
}
