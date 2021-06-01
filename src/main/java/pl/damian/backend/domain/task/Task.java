package pl.damian.backend.domain.task;

import java.util.List;

public class Task {
    private String id;
    private String taskName;
    private boolean finished;
    private List<String> notes;



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

    public boolean isFinished() {
        return finished;
    }

    public void finishTask() {
        this.finished = true;
    }

    public void changeTask(String newTask) { this.taskName = newTask;
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
