package com.david.todo.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ConstraintViolationException;

import com.david.todo.models.Task;
import com.david.todo.repositories.TaskRepository;
import com.david.todo.services.TaskService;
import com.david.todo.validation.StatusType;
import com.david.todo.validation.ValueOfEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * TaskController
 */
@RestController
@RequestMapping("tasks")
@Validated
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  private TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  // @RequestMapping("/new") // /api/v1/tasks/new
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<MyResponse<Task>> createTask(@Valid @RequestBody final Task task) {
    // return new Task();
    // Task t = taskRepository.save(task);
    Task t = taskService.create(task);
    MyResponse<Task> tt = new MyResponse<Task>(t, HttpStatus.CREATED, "created task successfullt");
    return new ResponseEntity<MyResponse<Task>>(tt, HttpStatus.CREATED);
  }

  @GetMapping
  public MyResponse<List<Task>> getTasks() {
    // List<Task> tasks = taskRepository.findAll();
    List<Task> tasks = taskService.getAll();
    MyResponse<List<Task>> res = new MyResponse<List<Task>>(tasks, HttpStatus.OK, "all tasks retrieved successfully");
    return res;
  }

  @GetMapping
  @RequestMapping("{id}")
  public MyResponse<Task> getOneTasks(@PathVariable Integer id, HttpServletResponse response) {
    // Optional<Task> op = taskRepository.findById(id);
    // Task task;
    // if (op.isPresent()) {
    // task = op.get();
    // MyResponse<Task> res = new MyResponse<Task>(task, HttpStatus.OK, "tasks
    // retrieved successfully");
    // return res;
    // } else {
    // response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    // return new MyResponse<>(null, HttpStatus.BAD_REQUEST, "unable to find the
    // task");
    // }

    Task task = taskService.getOne(id);
    if (task != null) {
      return new MyResponse<Task>(task, HttpStatus.OK, "tasks retrieved successfully");
    }
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return new MyResponse<>(null, HttpStatus.BAD_REQUEST, "unable to find the task");
  }

  @GetMapping
  @RequestMapping(value = "status/{status}")
  public MyResponse<List<Task>> getTasksByStatus(
      @PathVariable("status") @ValueOfEnum(enumClass = StatusType.class, message = "status must be one of pending, done, or inprogress") String status) {
    // List<Task> tasks = taskRepository.findByStatus(status);
    List<Task> tasks = taskService.getByStatus(status);
    MyResponse<List<Task>> res = new MyResponse<List<Task>>(tasks, HttpStatus.OK,
        status + " tasks retrieved successfully");
    return res;
  }

  // @GetMapping
  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  public MyResponse<Task> UpdateTask(@PathVariable("id") Integer id, @Valid @RequestBody final Task t, HttpServletResponse response) {
    Optional<Task> op = taskRepository.findById(id);
    if (op.isPresent()) {
      Task task = op.get();
      task.setStatus(t.getStatus());
      task.setTitle(t.getTitle());
      task.setDescription(t.getDescription());
      MyResponse<Task> res = new MyResponse<Task>(task, HttpStatus.OK, "tasks updated successfully");
      return res;
    }
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    return new MyResponse<>(null, HttpStatus.BAD_REQUEST, "unable to find the task");
  }
}

// use anotations for the version