package com.david.todo.repositories;

import com.david.todo.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TaskRepository
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {}