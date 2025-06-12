package com.devtaskpro.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private final String id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private String category;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String assignedTo;
    private String projectId;
    private int estimatedHours;
    private int actualHours;

    public enum Priority {
        CRITICAL(1),
        HIGH(2),
        MEDIUM(3),
        LOW(4);

        private final int value;

        Priority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Status {
        TODO,
        IN_PROGRESS,
        REVIEW,
        DONE
    }

    public Task(String title, String description, Priority priority, String category) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }
    public Status getStatus() { return status; }
    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    public String getCategory() { return category; }
    public void setCategory(String category) {
        this.category = category;
        this.updatedAt = LocalDateTime.now();
    }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        this.updatedAt = LocalDateTime.now();
    }
    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        this.updatedAt = LocalDateTime.now();
    }
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
        this.updatedAt = LocalDateTime.now();
    }
    public int getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
        this.updatedAt = LocalDateTime.now();
    }
    public int getActualHours() { return actualHours; }
    public void setActualHours(int actualHours) {
        this.actualHours = actualHours;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Task{id='%s', title='%s', priority=%s, status=%s, category='%s', dueDate=%s}",
                id, title, priority, status, category, dueDate);
    }
} 