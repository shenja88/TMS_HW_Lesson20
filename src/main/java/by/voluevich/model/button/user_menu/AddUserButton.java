package by.voluevich.model.button.user_menu;

import by.voluevich.entity.Address;
import by.voluevich.entity.Telephone;
import by.voluevich.entity.User;
import by.voluevich.model.button.ActionButton;
import by.voluevich.service.exception.MenuItemNotFoundException;
import by.voluevich.service.utils.Dependencies;
import by.voluevich.service.utils.Input;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddUserButton implements ActionButton {
    @Override
    public String name() {
        return "Add new user.";
    }

    @Override
    public void tap() {
        String log = Input.getString("Enter login.");
        String pass = Input.getString("Enter password.");

        List<Telephone> listAllTel = Dependencies.getTelephoneController().getTelephones();
        List<Telephone> selNum = new ArrayList<>();
        while (true) {
            for (int i = 0; i < listAllTel.size(); i++) {
                System.out.println((i + 1) + " - " + listAllTel.get(i).getNumber());
            }
            int selectNum = Input.getInt("Select a phone number.");
            if(selectNum < 1 || selectNum > listAllTel.size()){
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                selNum.add(listAllTel.get(selectNum - 1));
                break;
            }
        }

        List<Address> listAllAdd = Dependencies.getAddressController().getAddresses();
        List<Address> selAdd = new ArrayList<>();
        while (true) {
            for (int i = 0; i < listAllAdd.size(); i++) {
                System.out.println((i + 1) + " - " + listAllAdd.get(i).getStreet() + ", " + listAllAdd.get(i).getHome());
            }
            int selectNum = Input.getInt("Select address.");
            if(selectNum < 1 || selectNum > listAllAdd.size()){
                try {
                    throw new MenuItemNotFoundException();
                } catch (MenuItemNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                selAdd.add(listAllAdd.get(selectNum - 1));
                break;
            }
        }
        try {
            Dependencies.getUserController().addUser(new User(log, pass, selNum, selAdd));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
