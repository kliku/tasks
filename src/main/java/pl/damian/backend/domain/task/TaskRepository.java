package pl.damian.backend.domain.task;

import java.util.List;

public interface TaskRepository {
    List<Task> add(Task task);

    List<Task> getAll();

    void remove(Task task);

    void update(Task task);

}
