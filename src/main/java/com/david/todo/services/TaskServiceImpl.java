package com.david.todo.services;

import java.util.List;
import java.util.Optional;

import com.david.todo.models.Task;
import com.david.todo.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * TaskServiceImpl
 */
@Service
@Profile("dev")
public class TaskServiceImpl implements TaskService {

  private TaskRepository taskRepository;

  // @Inject
  @Autowired
  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task create(Task t) {
    return taskRepository.save(t);
  }

  @Override
  public List<Task> getAll() {
    List<Task> tasks = taskRepository.findAll();
    return tasks;
  }

  @Override
  public List<Task> getByStatus(String status) {
    List<Task> tasks = taskRepository.findByStatus(status);
    return tasks;
  }

  @Override
  public Task getOne(Integer id) {
    Optional<Task> op = taskRepository.findById(id);
    if (op.isPresent()) {
      return op.get();
    }
    return null;
  }

}