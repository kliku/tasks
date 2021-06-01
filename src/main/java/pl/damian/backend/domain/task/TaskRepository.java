package pl.damian.backend.domain.task;

import java.util.List;

public interface TaskRepository {
    void add(Task task);
    List<Task> getAll();
    void remove(Task task);
}
