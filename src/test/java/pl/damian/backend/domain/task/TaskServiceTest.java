package pl.damian.backend.domain.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.damian.backend.infrastructure.repository.TaskRepositoryInFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(new TaskRepositoryInFile());
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
        assertThrows(RuntimeException.class, () -> taskService.addNoteToTask("das", "dasdasdd"));
    }
    @Test
    void whenFinishTaskThenShouldChangeStatus() {
        //GIVEN
        Task task = taskService.createTask("Pójdź do sklepu");
        //WHEN
        Task finishedTask = taskService.toggleTask(task.getId());
        //THEN
        assertTrue(finishedTask.isFinished());
        assertThrows(RuntimeException.class, () -> taskService.toggleTask("hgadfgas"));
    }
    @Test
    void whenDeleteTaskThenGetAllShouldNotContainsThisTask() {
        //GIVEN
        Task task = taskService.createTask("Pójdź do sklepu");
        int numberOfTasks = taskService.getTasks().size();
        int expectedNumberOfTasksAfterDelete = numberOfTasks - 1;
        //WHEN
        taskService.deleteTask(task.getId()); // ID
        //THEN
        assertEquals(expectedNumberOfTasksAfterDelete,taskService.getTasks().size());
        assertThrows(RuntimeException.class, () -> taskService.deleteTask("gasfas"));
    }
    @Test
    void whenChangeTaskNameThenShouldChangeTaskName() {
        //GIVEN
        Task task = taskService.createTask("Zrób obiad");
        String newTask = "Zrób obiad i kolacje";
        //WHEN
        Task changedTask = taskService.changeTaskName(task.getId(),newTask);
        //THEN
        assertEquals("Zrób obiad i kolacje", changedTask.getTaskName());
        assertThrows(RuntimeException.class, () -> taskService.changeTaskName("fafasf","Zrób obiad i kolacje"));
    }
    @Test
    void whenAddedMultiplyTasksThenGetTasksShouldReturnThem() {
        //GIVEN
        taskService.createTask("Zrób obiad");
        taskService.createTask("Zrób obiad i kolacje");
        taskService.createTask("Zrób śniadanie");
        int numberOfTasks = 3;
        //WHEN
        List<Task> tasks = taskService.getTasks();
        //THEN
        assertEquals(numberOfTasks,tasks.size());
    }
}