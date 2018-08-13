package service.impl;

import dao.Transaction;
import dao.impl.AccidentDAO;
import pojo.dto.PageDTO;
import pojo.entity.Accident;
import service.AccidentService;

import java.sql.SQLException;
import java.util.List;

public class AccidentServiceImpl implements AccidentService {

    @Override
    public List<Accident> getAccidentList(PageDTO pageDTO){
        AccidentDAO accidentDAO = new AccidentDAO();
        Transaction transaction = new Transaction();
        List<Accident> accidentList = null;

        transaction.beginTransaction(accidentDAO);

        pageDTO.setElementsCount(accidentDAO.getAccidentsCount());
        pageDTO.calculatePagesCount();
        accidentList = accidentDAO.findAll(pageDTO);

        try {
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
        }
        transaction.endTransaction();

        return accidentList;
    }
}
