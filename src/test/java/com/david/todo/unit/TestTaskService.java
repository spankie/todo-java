package com.david.todo.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.david.todo.models.Task;
import com.david.todo.repositories.TaskRepository;
import com.david.todo.services.TaskService;
import com.david.todo.services.TaskServiceImpl;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

/**
 * TestTaskService
 */
public class TestTaskService {

  @Test
  public void TestGetOneTask() {
    TaskRepository mockTaskRepository = mock(TaskRepository.class);
    when(mockTaskRepository.findById(anyInt())).thenReturn(Optional.of(new Task()));
    TaskService service = new TaskServiceImpl(mockTaskRepository);

    Task t = service.getOne(1);

    assertNotNull(t);
  }

  @Test
  public void throwExceptionWhenIdIsNull() {
    TaskRepository mockTaskRepository = mock(TaskRepository.class);
    when(mockTaskRepository.findById(null)).thenThrow(new IllegalArgumentException("Invalid argument"));
    // thenReturn(Optional.of(new Task()));
    TaskService service = new TaskServiceImpl(mockTaskRepository);
    try {
      Task t = service.getOne(null);
      fail("Should throw illegalArgumentException");
    } catch (IllegalArgumentException e) {
      // TODO: handle exception
      assertEquals("Invalid argument", e.getMessage());
    }

  }
}