package service;

import pojo.dto.PageDTO;
import pojo.entity.Accident;

import java.util.List;

public interface AccidentService {

    List<Accident> getAccidentList(PageDTO pageDTO);

}
