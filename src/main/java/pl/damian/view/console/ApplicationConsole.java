package pl.damian.view.console;

import pl.damian.backend.api.Tasks;
import pl.damian.view.ApplicationView;

import java.util.Scanner;

public class ApplicationConsole implements ApplicationView {

    private final Tasks tasks = new Tasks();

    @Override
    public void runApplication() {

        do {
            System.out.println("Wybierz opcje:\n [0] - wyjdź \n [1] - dodaj zadanie \n [2] - dodaj notatkę " +
                    "\n [3] - zakończ zadanie \n [4] - usuń zadanie \n [5] - zmiana zadania");
            String nrOption = new Scanner(System.in).nextLine();
            switch (nrOption) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    addTask();
                    break;
                case "2":
                    addNoteToTask();
                    break;
                case "3":
                    finishTask();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    changeTask();
                    break;
            }
            System.out.println(tasks.getTasks());
        } while (true);
    }

    private void changeTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        System.out.println("Zmień nazwę zadania");
        String newTask = new Scanner(System.in).nextLine();
        tasks.changeTask(taskId, newTask);

    }

    private void deleteTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        tasks.deleteTask(taskId);
    }

    private void finishTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        tasks.finishTask(taskId);
    }

    private void addNoteToTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        System.out.println("Wpisz notatkę do zadania");
        String note = new Scanner(System.in).nextLine();
        tasks.addNoteToTask(taskId, note);
    }

    private void addTask() {
        System.out.println("Wpisz nazwę zadania1");
        String taskName = new Scanner(System.in).nextLine();
        tasks.createTask(taskName);
    }
}
