package pl.damian.backend.infrastructure.repository;

import pl.damian.backend.domain.task.Task;
import pl.damian.backend.domain.task.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryInMemory implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public void remove(Task task) {
        tasks.remove(task);
    }

    @Override
    public void update(Task task) {

    }
}
