package service;

import domain.dto.PageDTO;
import domain.entity.Accident;
import service.exception.ServiceException;

import java.util.List;

public interface AccidentService {

    List<Accident> getAccidentList(PageDTO pageDTO) throws ServiceException;

}
