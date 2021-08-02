package pl.damian.backend.domain.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TaskServiceMockitoTest {
    private TaskService taskService;
    private TaskRepository taskRepository;


    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(taskRepository);
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
        verify(taskRepository, times(1)).add(any(Task.class));
    }

    @Test
    void  whenAddNoteToTaskThenTaskShouldContainsNote() {
        //GIVEN
        Task task = taskService.createTask("Pójdź do sklepu");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.getAll()).thenReturn(tasks);
        String note = "Kup mleko";
        //WHEN
        Task addedNoteToTask = taskService.addNoteToTask(task.getId(), note);
        //THEN
        assertNotNull(addedNoteToTask);
        assertEquals(task.getId(),addedNoteToTask.getId());
        assertTrue(addedNoteToTask.getNotes().contains(note));
        verify(taskRepository, times(1)).update(any(Task.class));
    }

    @Test
    void whenInvalidAddNoteToTaskThenShouldThrowException() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(RuntimeException.class, () -> taskService.addNoteToTask("das", "dasdasdd"));
        verify(taskRepository, times(1)).getAll();
        verify(taskRepository, never()).update(any(Task.class));
    }

    @Test
    void whenFinishTaskThenShouldReturnFinishedStatus() {
        //GIVEN
        Task task = taskService.createTask("Pójdź do sklepu");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.getAll()).thenReturn(tasks);
        //WHEN
        Task finishedTask = taskService.toggleTask(task.getId());
        //THEN
        assertTrue(finishedTask.isFinished());
        verify(taskRepository, times(1)).update(any(Task.class));
    }

    @Test
    void whenFinishTaskWithInvalidIdThenShouldThrowException() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(RuntimeException.class, () -> taskService.toggleTask("hgadfgas"));
        verify(taskRepository, never()).update(any(Task.class));
    }

    @Test
    @Disabled
    void whenDeleteTaskThenGetAllShouldNotContainsThisTask() {
        //GIVEN
        Task task = taskService.createTask("Pójdź do sklepu");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.getAll()).thenReturn(tasks);
        int numberOfTasks = taskService.getTasks().size();
        int expectedNumberOfTasksAfterDelete = numberOfTasks - 1;
        //WHEN
        taskService.deleteTask(task.getId()); // ID
        //THEN
        assertEquals(expectedNumberOfTasksAfterDelete,taskService.getTasks().size());
        verify(taskRepository, times(1)).remove(any(Task.class));
    }

    @Test
    void whenDeleteTaskWhenProvidedInvalidIdThenShouldThrowException() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(RuntimeException.class, () -> taskService.deleteTask("gasfas"));
        verify(taskRepository, times(0)).remove(any(Task.class));
    }

    @Test
    void whenChangeTaskNameThenShouldChangeTaskName() {
        //GIVEN
        Task task = taskService.createTask("Zrób obiad");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.getAll()).thenReturn(tasks);
        String newTask = "Zrób obiad i kolacje";
        //WHEN
        Task changedTask = taskService.changeTaskName(task.getId(),newTask);
        //THEN
        assertEquals("Zrób obiad i kolacje", changedTask.getTaskName());
        verify(taskRepository, times(1)).update(any(Task.class));
    }

    @Test
    void whenChangeTaskNameWithProvidedInvalidIdThenThrowException() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(RuntimeException.class, () -> taskService.changeTaskName("fafasf","Zrób obiad i kolacje"));
        verify(taskRepository, never()).update(any(Task.class));
    }
}
