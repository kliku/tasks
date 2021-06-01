package pl.damian.backend.domain.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.damian.backend.infrastructure.repository.TaskRepositoryInMemory;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(new TaskRepositoryInMemory());
    }

    @Test
    void whenCreateTaskThenTaskShouldBeReturned() {
        //GIVEN
        String taskName = "zrób zakupy";
        //WHEN
        Task task = taskService.createTask(taskName);
        //THEN
        assertNotNull(task);
        assertTrue(task.getId().length() > 0);
        assertEquals(taskName, task.getTaskName());
    }

    @Test
    void whenAddNoteToTaskThenTaskShouldContainsNote() {
        //GIVEN
        Task task = taskService.createTask("Pójdź do sklepu");
        String note = "Kup mleko";
        //WHEN
        Task addedNoteToTask = taskService.addNoteToTask(task.getId(), note);
        //THEN
        assertNotNull(addedNoteToTask);
        assertEquals(task.getId(),addedNoteToTask.getId());
        assertTrue(addedNoteToTask.getNotes().contains(note));
    }
    @Test
    void whenInvalidAddNoteToTaskThenShouldThrowException() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(RuntimeException.class, () -> taskService.addNoteToTask("eerere", "dasdasdd"));
    }
}