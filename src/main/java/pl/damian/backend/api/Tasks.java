package pl.damian.backend.api;

import pl.damian.backend.domain.task.Task;
import pl.damian.backend.domain.task.TaskService;
import pl.damian.backend.infrastructure.repository.TaskRepositoryInFile;

import java.util.List;

public class Tasks {
    private final TaskService taskService = new TaskService(new TaskRepositoryInFile());


    public void createTask(String taskName) {
        taskService.createTask(taskName);
    }

    public void addNoteToTask(String taskId, String note) {
        taskService.addNoteToTask(taskId, note);
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


    public void changeTask(String taskId, String newTask) {
        taskService.changeTaskName(taskId, newTask);
    }
}
