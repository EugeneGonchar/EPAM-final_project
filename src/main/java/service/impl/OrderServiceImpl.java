package service.impl;

import dao.OrderDAO;
import dao.Transaction;
import dao.UserDAO;
import dao.exception.dao.DAOException;
import dao.factory.DAOFactory;
import domain.dto.FullOrderDTO;
import domain.dto.FullUserOrderDTO;
import domain.dto.PageDTO;
import domain.entity.Car;
import domain.entity.Order;
import domain.entity.User;
import service.OrderService;
import service.exception.ServiceException;
import service.util.Hash;
import service.util.PasswordCreator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<FullOrderDTO> getFullUserOrders(User user, PageDTO pageDTO) throws ServiceException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();
        List<FullOrderDTO> fullOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        try {
            pageDTO.setElementsCount(orderDAO.getFullOrdersCountByUserId(user.getId()));
            pageDTO.calculatePagesCount();
            fullOrderDTOList = orderDAO.getFullOrdersById(user.getId(), pageDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving list of orders with users", e);
        } finally {
            transaction.endTransaction();
        }
        return fullOrderDTOList;
    }

    @Override
    public List<FullUserOrderDTO> getOrdersList(PageDTO pageDTO) throws ServiceException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();
        List<FullUserOrderDTO> fullUserOrderDTOList = null;

        transaction.beginTransaction(orderDAO);

        try {
            pageDTO.setElementsCount(orderDAO.getFullOrdersCount());
            pageDTO.calculatePagesCount();
            fullUserOrderDTOList = orderDAO.getFullOrders(pageDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving order list", e);
        } finally {
            transaction.endTransaction();
        }
        return fullUserOrderDTOList;
    }

    @Override
    public void insertOrder(Order order) throws ServiceException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(orderDAO);

        try {
            orderDAO.insertOrder(order);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during inserting order", e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public User insertOrder(Order order, User user) throws ServiceException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        Transaction transaction = new Transaction();
        User registeredUser = null;

        user.setLogin(user.getEmail());
        String generatedPassword = PasswordCreator.createPassword();
        user.setPassword(Hash.getCryptoSha256(generatedPassword));

        transaction.beginTransaction(orderDAO, userDAO);

        try {
            userDAO.insertUser(user);
            registeredUser = userDAO.getUserByLogin(user.getLogin());
            order.setUserId(registeredUser.getId());
            orderDAO.insertOrder(order);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during inserting order", e);
        } finally {
            transaction.endTransaction();
        }

        registeredUser.setPassword(generatedPassword);

        return registeredUser;
    }

    public void updateOrderStatus(int orderId, String status) throws ServiceException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(orderDAO);

        try {
            orderDAO.updateOrder(orderId, status);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating order's status", e);
        } finally {
            transaction.endTransaction();
        }
    }

    public void updateOrderStatus(int orderId, String status, String description) throws ServiceException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        Transaction transaction = new Transaction();

        transaction.beginTransaction(orderDAO);

        try {
            orderDAO.updateOrder(orderId, status, description);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during updating order status", e);
        } finally {
            transaction.endTransaction();
        }
    }

    public static BigDecimal getCalculatedTotalCost(Car car, int rentDays){
        return car.getRental4Day().multiply(new BigDecimal(rentDays));
    }
}
