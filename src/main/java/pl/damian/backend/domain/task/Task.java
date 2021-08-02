package pl.damian.backend.domain.task;

import java.util.List;
import java.util.Objects;

public class Task {
    private String id;
    private String taskName;
    private boolean finished;
    private List<String> notes;

    public Task() {
    }

    public Task(String id, String taskName, List<String> notes) {
        this.id = id;
        this.taskName = taskName;
        this.notes = notes;
        this.finished = false;
    }

    public String getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public List<String> getNotes() {
        return notes;
    }
    public void addNote(String note) {
        notes.add(note);
    }
    public boolean isFinished() {
        return finished;
    }

    public void toggleTask() {
        this.finished = !isFinished();
    }

    public void changeTask(String newTask) { this.taskName = newTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return finished == task.finished && Objects.equals(id, task.id) && Objects.equals(taskName, task.taskName)
                && Objects.equals(notes, task.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, finished, notes);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", taskName='" + taskName + '\'' +
                ", finished=" + finished +
                ", notes=" + notes +
                '}';
    }


}
