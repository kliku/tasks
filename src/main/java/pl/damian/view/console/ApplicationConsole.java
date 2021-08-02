package pl.damian.view.console;

import pl.damian.backend.api.ApplicationAPI;
import pl.damian.backend.domain.exception.DomainException;
import pl.damian.backend.domain.task.TaskService;
import pl.damian.backend.infrastructure.repository.TaskRepositoryInFile;
import pl.damian.view.ApplicationView;

import java.util.Scanner;

public class ApplicationConsole implements ApplicationView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private final ApplicationAPI applicationAPI = new ApplicationAPI(new TaskService(new TaskRepositoryInFile()));

    @Override
    public void runApplication() {
        do {
            System.out.println("Wybierz opcje:\n [0] - wyjdź \n [1] - dodaj zadanie \n [2] - dodaj notatkę " +
                    "\n [3] - zmień status zadania \n [4] - usuń zadanie \n [5] - zmiana zadania \n " +
                    "[6] - sprawdź pogodę\n [7] - sprawdź pogodę na przyszłe dni\n " +
                    "[8] - sprawdź pogodę na wcześniejsze dni" );
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
                    toggleTask();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    changeTask();
                    break;
                case "6":
                    checkCurrentWeather();
                    break;
                case "7":
                    checkForecastWeather();
                    break;
                case "8":
                    checkHistoryWeather();
                    break;
            }
        } while (true);
    }

    private void checkHistoryWeather() {
        System.out.println("Wpisz miejscowość");
        String city = new Scanner(System.in).nextLine();
        System.out.println("Wpisz date [yyyy-MM-dd]");
        String date = new Scanner(System.in).nextLine();
        try {
            String weatherInformation = applicationAPI.checkHistoryWeather(city, date);
            System.out.println("Historia pogody w dniu: " + date + "\n" + weatherInformation);
        } catch (DomainException e) {
            System.out.println(ANSI_RED + "Wystąpił błąd. " + e.getMessage() + ANSI_RESET);
        }
    }

    private void checkCurrentWeather() {
        System.out.println("Wpisz miejscowość");
        String city = new Scanner(System.in).nextLine();
        String weatherInformation = applicationAPI.checkCurrentWeather(city);
        System.out.println("Aktualna pogoda: \n" + weatherInformation);
    }

    private void checkForecastWeather() {
        System.out.println("Wpisz miejscowość");
        String city = new Scanner(System.in).nextLine();
        System.out.println("Wpisz ilość dni prognozy(max 3 dni)");
        int days = new Scanner(System.in).nextInt();
        String weatherInformation = applicationAPI.checkForecastWeather(city, days);
        System.out.println("Pogoda na przyszłe dni: \n" + weatherInformation);
    }

    private void changeTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        System.out.println("Zmień nazwę zadania");
        String newTask = new Scanner(System.in).nextLine();
        applicationAPI.changeTaskName(taskId, newTask);
    }

    private void deleteTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        applicationAPI.deleteTask(taskId);
    }

    private void toggleTask() {//toggleTask
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        applicationAPI.toggleTask(taskId);
    }

    private void addNoteToTask() {
        System.out.println("Wpisz id zadania");
        String taskId = new Scanner(System.in).nextLine();
        System.out.println("Wpisz notatkę do zadania");
        String note = new Scanner(System.in).nextLine();
        applicationAPI.addNoteToTask(taskId, note);
    }

    private void addTask() {
        System.out.println("Wpisz nazwę zadania1");
        String taskName = new Scanner(System.in).nextLine();
        applicationAPI.createTask(taskName);
    }
}
