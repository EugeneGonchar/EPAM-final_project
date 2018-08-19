package service.impl;

import dao.AccidentDAO;
import dao.Transaction;
import dao.exception.dao.DAOException;
import dao.factory.DAOFactory;
import domain.dto.PageDTO;
import domain.entity.Accident;
import service.AccidentService;
import service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class AccidentServiceImpl implements AccidentService {

    @Override
    public List<Accident> getAccidentList(PageDTO pageDTO) throws ServiceException{
        AccidentDAO accidentDAO = DAOFactory.getInstance().getAccidentDAO();
        Transaction transaction = new Transaction();
        List<Accident> accidentList = null;

        transaction.beginTransaction(accidentDAO);

        try {
            pageDTO.setElementsCount(accidentDAO.getAccidentsCount());
            pageDTO.calculatePagesCount();
            accidentList = accidentDAO.getAll(pageDTO);
            try {
                transaction.commit();
            } catch (SQLException e) {
                transaction.rollback();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception throws on service layer during retrieving list of accidents", e);
        } finally {
            transaction.endTransaction();
        }
        return accidentList;
    }
}
