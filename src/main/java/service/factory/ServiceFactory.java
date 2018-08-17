package service.factory;

import service.*;
import service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();
    private static final AddressService addressService = new AddressServiceImpl();
    private static final CarService carService = new CarServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final RoleService roleService = new RoleServiceImpl();
    private static final AccidentService accidentService = new AccidentServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public CarService getCarService() {
        return carService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public AccidentService getAccidentService() {
        return accidentService;
    }
}
