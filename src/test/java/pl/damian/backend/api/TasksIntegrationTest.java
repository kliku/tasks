package pl.damian.backend.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.damian.backend.domain.task.Task;
import pl.damian.backend.domain.task.TaskRepository;
import pl.damian.backend.domain.task.TaskService;
import pl.damian.backend.infrastructure.repository.TaskRepositoryInFile;

import static org.junit.jupiter.api.Assertions.*;

public class TasksIntegrationTest {
    private ApplicationAPI applicationAPI;
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepositoryInFile();
        applicationAPI = new ApplicationAPI(new TaskService(taskRepository));
    }

    @Test
    void whenCreateTaskThenShouldInsertTaskToDatabase() {
        //GIVEN
        String taskName = "Zrób zakupy";
        //WHEN
        applicationAPI.createTask(taskName);
        //THEN
        assertEquals(1, taskRepository.getAll().size());
    }

    @Test
    void whenCreateTaskThenShouldReturnedValidTask() {
        //GIVEN
        String taskName = "Zrób obiad";
        //WHEN
        Task task = applicationAPI.createTask(taskName);
        //THEN
        assertNotNull(task);
        assertNotNull(task.getId());
        assertTrue(task.getId().length() > 0);
        assertEquals(taskName, task.getTaskName());
        assertFalse(task.isFinished());
        assertNotNull(task.getNotes());
        assertEquals(0, task.getNotes().size());
    }

    @Test
    void whenAddNoteToTaskThenShouldAddNoteToDatabase() {
        //GIVEN
        String taskName = "Zrób kolacje";
        Task task = applicationAPI.createTask(taskName);
        String note = "Vegetariańską";
        String taskId = task.getId();
        //WHEN
        applicationAPI.addNoteToTask(taskId, note);
        //THEN
        assertTrue(getTask(taskId).getNotes().contains(note));
    }

    @Test
    void whenAddNoteToTaskThenShouldReturnedTaskWithNote() {
        //GIVEN
        String taskName = "Zrób kolacje";
        Task task = applicationAPI.createTask(taskName);
        String note = "Vegetariańską";
        String taskId = task.getId();
        //WHEN
        Task taskWithNote = applicationAPI.addNoteToTask(taskId, note);
        //THEN
        assertTrue(taskWithNote.getNotes().contains(note));
    }

    @Test
    void whenCreateTaskThenShouldAddTaskToList() {
        //GIVEN
        String taskName = "Zrób kolacje";
        applicationAPI.createTask(taskName);
        //WHEN
        //THEN
        assertEquals(1, applicationAPI.getTasks().size());
    }

    @Test
    void whenToggleStatusTaskThenShouldChangeStatus() {
        //GIVEN
        String taskName = "Zrób kolacje";
        Task task = applicationAPI.createTask(taskName);
        String taskId = task.getId();
        //WHEN
        applicationAPI.toggleTask(taskId);
        //THEN
        assertTrue(getTask(taskId).isFinished());
        assertEquals(!task.isFinished(), getTask(taskId).isFinished());
    }

    @Test
    void whenDeleteTaskThenShouldRemoveTask() {
        //GIVEN
        String taskName = "Zrób kolacje";
        Task task = applicationAPI.createTask(taskName);
        String taskId = task.getId();
        int sizeBeforeDelete = applicationAPI.getTasks().size();
        //WHEN
        applicationAPI.deleteTask(taskId);
        //THEN
        assertEquals(0, applicationAPI.getTasks().size());
        assertEquals(sizeBeforeDelete, applicationAPI.getTasks().size() + 1);
    }

    @Test
    void whenChangeTaskNameThenShouldNewNameTask() {
        //GIVEN
        String taskName = "Zrób kolacje";
        Task task = applicationAPI.createTask(taskName);
        String taskId = task.getId();
        //WHEN
        applicationAPI.changeTaskName(taskId, "Zrób obiad");
        //THEN
        assertNotEquals("Zrób kolacje", getTask(taskId).getTaskName());
        assertEquals("Zrób obiad", getTask(taskId).getTaskName());
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
