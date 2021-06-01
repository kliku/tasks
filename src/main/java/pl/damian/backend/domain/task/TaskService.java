package pl.damian.backend.domain.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String taskName) {
        Task task = new Task(UUID.randomUUID().toString(), taskName, new ArrayList<>());
        taskRepository.add(task);
        return task;
    }

    public Task addNoteToTask(String taskId, String note) {
        // get task by task id
        Task task = getTask(taskId);

        // add note to this task
        task.getNotes().add(note);
        return task;
    }

    public List<Task> getTasks() {
        return taskRepository.getAll();
    }

    public void finishTask(String taskId) {
        for (Task task : taskRepository.getAll()) {
            if (task.getId().equals(taskId)) {
                task.finishTask();
            }
        }
    }

    public void deleteTask(String taskId) {
        for (int i = 0; i < taskRepository.getAll().size(); i++) {
            Task task = taskRepository.getAll().get(i);
            if (task.getId().equals(taskId)) {
                taskRepository.remove(task);
            }
        }
    }

    public void changeTask(String taskId, String newTask) {
        for (Task task : taskRepository.getAll()) {
            if (task.getId().equals(taskId)) {
                task.changeTask(newTask);
            }
        }
    }

    private Task getTask(String taskId) {
        Task foundTask = null;
        for (Task task : taskRepository.getAll()) {
            if (task.getId().equals(taskId)) {
                foundTask = task;
                break;
            }
        }
        if (foundTask == null) {
            throw new RuntimeException("Task not found with provided ID: " + taskId);
        } else {
            return foundTask;
        }
    }
}
