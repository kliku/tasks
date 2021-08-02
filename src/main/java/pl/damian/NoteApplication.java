package pl.damian;

import pl.damian.view.ApplicationView;
import pl.damian.view.console.ApplicationConsole;


public class NoteApplication {
    public static void main(String[] args) {
        ApplicationView applicationView = new ApplicationConsole();
        applicationView.runApplication();
    }
}
