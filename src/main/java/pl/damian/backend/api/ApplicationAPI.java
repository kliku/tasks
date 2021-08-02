package pl.damian.backend.api;

import pl.damian.backend.domain.task.Task;
import pl.damian.backend.domain.task.TaskService;
import pl.damian.backend.domain.weather.WeatherService;

import java.util.List;

public class ApplicationAPI {
    private final TaskService taskService;
    private final WeatherService weatherService = new WeatherService();

    public ApplicationAPI(TaskService taskService) {
        this.taskService = taskService;
    }


    public Task createTask(String taskName) {
        return taskService.createTask(taskName);
    }

    public Task addNoteToTask(String taskId, String note) {
        return taskService.addNoteToTask(taskId, note);
    }

    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    public void toggleTask(String taskId) {
        taskService.toggleTask(taskId);
    }

    public void deleteTask(String taskId) {
        taskService.deleteTask(taskId);
    }

    public void changeTaskName(String taskId, String newTask) {
        taskService.changeTaskName(taskId, newTask);
    }

    public String checkCurrentWeather(String city) {
        return weatherService.checkCurrentWeather(city);
    }

    public String checkForecastWeather(String city, int days) {
        return weatherService.checkForecastWeather(city, days);
    }

    public String checkHistoryWeather(String city, String date) {
        return weatherService.checkHistoryWeather(city,date);
    }
}
