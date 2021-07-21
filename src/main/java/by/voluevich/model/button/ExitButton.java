package by.voluevich.model.button;


import by.voluevich.Application;

public class ExitButton implements ActionButton {

    @Override
    public String name() {
        return "Exit.";
    }

    @Override
    public void tap() {
            System.out.println("Good bye!");
            Application.started = false;
        }
    }

