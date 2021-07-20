package by.voluevich;

import by.voluevich.model.menu.MainMenu;

public class Application {
    public static boolean started = true;

    public Application() {
    }

    public void start() {
        MainMenu mainMenu = new MainMenu();
        while (started){
            mainMenu.selectButton();
        }
    }
}
