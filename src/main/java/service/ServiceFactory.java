package service;

import service.impl.AddressServiceImpl;
import service.impl.CarServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();
    private static final AddressService addressService = new AddressServiceImpl();
    private static final CarService carService = new CarServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public static AddressService getAddressService() {
        return addressService;
    }

    public static CarService getCarService() {
        return carService;
    }

    public static OrderService getOrderService() {
        return orderService;
    }
}
