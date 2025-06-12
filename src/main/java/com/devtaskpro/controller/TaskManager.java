package com.devtaskpro.controller;

import com.devtaskpro.model.Project;
import com.devtaskpro.model.Task;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final Map<String, Project> projects;
    private final Map<String, Task> tasks;

    public TaskManager() {
        this.projects = new HashMap<>();
        this.tasks = new HashMap<>();
    }

    // Project Management
    public Project createProject(String name, String description) {
        Project project = new Project(name, description);
        projects.put(project.getId(), project);
        return project;
    }

    public void deleteProject(String projectId) {
        Project project = projects.remove(projectId);
        if (project != null) {
            project.getTasks().forEach(task -> tasks.remove(task.getId()));
        }
    }

    public Project getProject(String projectId) {
        return projects.get(projectId);
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    // Task Management
    public Task createTask(String projectId, String title, String description, 
                         Task.Priority priority, String category) {
        Project project = projects.get(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found: " + projectId);
        }

        Task task = new Task(title, description, priority, category);
        tasks.put(task.getId(), task);
        project.addTask(task);
        return task;
    }

    public void updateTaskStatus(String taskId, Task.Status newStatus) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setStatus(newStatus);
        }
    }

    public void deleteTask(String taskId) {
        Task task = tasks.remove(taskId);
        if (task != null && task.getProjectId() != null) {
            Project project = projects.get(task.getProjectId());
            if (project != null) {
                project.removeTask(taskId);
            }
        }
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    // Task Queries
    public List<Task> getTasksByProject(String projectId) {
        Project project = projects.get(projectId);
        return project != null ? project.getTasks() : Collections.emptyList();
    }

    public List<Task> getTasksByPriority(Task.Priority priority) {
        return tasks.values().stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByStatus(Task.Status status) {
        return tasks.values().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> getOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        return tasks.values().stream()
                .filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(now))
                .collect(Collectors.toList());
    }

    // Project Statistics
    public Map<Task.Status, Long> getProjectTaskStatusCount(String projectId) {
        Project project = projects.get(projectId);
        if (project == null) return Collections.emptyMap();

        return project.getTasks().stream()
                .collect(Collectors.groupingBy(
                        Task::getStatus,
                        Collectors.counting()
                ));
    }

    public double getProjectCompletionRate(String projectId) {
        Project project = projects.get(projectId);
        return project != null ? project.getProjectProgress() : 0.0;
    }

    // Team Management
    public void assignTaskToUser(String taskId, String username) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setAssignedTo(username);
        }
    }

    public List<Task> getTasksAssignedToUser(String username) {
        return tasks.values().stream()
                .filter(task -> username.equals(task.getAssignedTo()))
                .collect(Collectors.toList());
    }
} 