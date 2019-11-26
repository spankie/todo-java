package com.david.todo.controllers;

import com.david.todo.configuration.ConfigProperties;
import com.david.todo.models.Task;
import com.david.todo.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * TaskController
 */
@RestController
@RequestMapping("tasks")
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ConfigProperties config;

  @PostMapping
  // @RequestMapping("/new") // /api/v1/tasks/new
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<MyResponse<Task>> createTask(@RequestBody final Task task) {
    // return new Task();
    Task t = taskRepository.save(task);
    // ResponseEntity<Task> r = .of(t);
    // MyResponse<Task> rr = new MyResponse<Task>();
    // ResponseEntity<Task> re = new ResponseEntity<Task>(t, HttpStatus.OK);
    // return new MyResponse<ResponseEntity<Task>>(re, HttpStatus.OK, "task created successfully");
    MyResponse<Task> tt = new MyResponse<Task>(t, HttpStatus.CREATED, "created task successfullt");
    return new ResponseEntity<MyResponse<Task>>(tt, HttpStatus.CREATED);
  }


}

// use anotations for the version