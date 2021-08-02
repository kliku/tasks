package pl.damian.backend.domain.task;

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
        Task task = getTask(taskId);
        task.addNote(note);
        taskRepository.update(task);
        return task;
    }


    public List<Task> getTasks() {
        return taskRepository.getAll();
    }

    public Task toggleTask(String taskId) {
        Task task = getTask(taskId);
        task.toggleTask();
        taskRepository.update(task);
        return task;
    }

    public void deleteTask(String taskId) {
        Task task = getTask(taskId);
        taskRepository.remove(task);
    }

    public Task changeTaskName(String taskId, String newTask) {
        Task task = getTask(taskId);
        task.changeTask(newTask);
        taskRepository.update(task);
        return task;
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
