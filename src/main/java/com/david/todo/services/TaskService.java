package com.david.todo.services;

import java.util.List;

import com.david.todo.models.Task;

/**
 * TaskService
 */
public interface TaskService {

  public Task create(Task t);

  public List<Task> getAll();

  public Task getOne(Integer id);

  public List<Task> getByStatus(String status);
}