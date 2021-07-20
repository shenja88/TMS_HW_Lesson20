package by.voluevich.model.button;


import by.voluevich.Application;
import by.voluevich.dao.ConnectionManager;

import java.sql.SQLException;

public class ExitButton implements ActionButton {

    @Override
    public String name() {
        return "Exit.";
    }

    @Override
    public void tap() {
        try {
            System.out.println("Good bye!");
            Application.started = false;
            ConnectionManager.getConnection().close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
