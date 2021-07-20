package by.voluevich.model.button;


import by.voluevich.model.menu.Menu;
import by.voluevich.service.exception.MenuItemNotFoundException;

public class BackButton implements ActionButton {
    private Menu menu;

    public BackButton(Menu menu) {
        this.menu = menu;
    }

    public BackButton() {
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public String name() {
        return "Back";
    }

    @Override
    public void tap() {
        try {
            menu.selectButton();
        } catch (MenuItemNotFoundException e) {
            e.getMessage();
        }
    }
}
