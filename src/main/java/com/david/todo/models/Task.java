package com.david.todo.models;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Task
 */
@Entity(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String status;

  @NotBlank
  @NotNull
  @Length(max = 150)
  private String title;
  private String description;
  @Column(name = "created_at")
  private Time createAt;
  @Column(name = "updated_at")
  private Time updatedAt;
  @Column(name = "completed_at")
  private Time completedAt;

  public Task() {}

  public Task(String title, String description, String status) {
    this.status = status;
    this.title = title;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Time getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Time createAt) {
    this.createAt = createAt;
  }

  public Time getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Time updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Time getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(Time completedAt) {
    this.completedAt = completedAt;
  }

}
