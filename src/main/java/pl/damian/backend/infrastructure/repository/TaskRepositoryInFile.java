package pl.damian.backend.infrastructure.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.damian.backend.domain.task.Task;
import pl.damian.backend.domain.task.TaskRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryInFile implements TaskRepository {
    private final File fileTasks = new File("tasks.json");
    private final ObjectMapper objectMapper = new ObjectMapper();


    public TaskRepositoryInFile() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileTasks);
            fileWriter.write("[]");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public List<Task> add(Task task) {
        try {
            List<Task> tasks = getAll();
            tasks.add(task);
            objectMapper.writeValue(fileTasks, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> getAll() {
        try {
            return objectMapper.readValue(fileTasks, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void remove(Task task) {
        try {
            List<Task> tasks = getAll();
            tasks.remove(task);
            objectMapper.writeValue(fileTasks, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Task task) {
        //get task from all provided by id
        Task oldTask = getTask(task.getId());
        //remove this task
        remove(oldTask);
        //add new task
        add(task);
    }

    private Task getTask(String taskId) {
        Task findTask = null;
        for (Task task : getAll()) {
            if (task.getId().equals(taskId)) {
                findTask = task;
            }
        }
        if (findTask == null) {
            throw new RuntimeException("If task not found ID: ");
        } else {
            return findTask;
        }
    }
}
