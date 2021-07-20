package by.voluevich.service.utils;

import by.voluevich.controllers.*;
import by.voluevich.dao.*;

public class Dependencies {
    private static final UsersDao USERS_DAO = new UsersDaoImpl();
    private static final AddressDao ADDRESS_DAO = new AddressDaoImpl();
    private static final TelephoneDao TELEPHONE_DAO = new TelephoneDaoImpl();
    private static final UserController USER_CONTROLLER = new UserControllerImpl();
    private static final AddressController ADDRESS_CONTROLLER = new AddressControllerImpl();
    private static final TelephoneController TELEPHONE_CONTROLLER = new TelephoneControllerImpl();

    public static UsersDao getUsersDao() {
        return USERS_DAO;
    }

    public static AddressDao getAddressDao() {
        return ADDRESS_DAO;
    }

    public static TelephoneDao getTelephoneDao() {
        return TELEPHONE_DAO;
    }

    public static UserController getUserController() {
        return USER_CONTROLLER;
    }

    public static AddressController getAddressController() {
        return ADDRESS_CONTROLLER;
    }

    public static TelephoneController getTelephoneController() {
        return TELEPHONE_CONTROLLER;
    }
}
