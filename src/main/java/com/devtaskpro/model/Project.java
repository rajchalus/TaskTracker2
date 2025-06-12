package com.devtaskpro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {
    private final String id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Task> tasks;
    private String repositoryUrl;
    private String teamLead;
    private List<String> teamMembers;
    private ProjectStatus status;

    public enum ProjectStatus {
        PLANNING,
        IN_PROGRESS,
        ON_HOLD,
        COMPLETED,
        CANCELLED
    }

    public Project(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.startDate = LocalDateTime.now();
        this.tasks = new ArrayList<>();
        this.teamMembers = new ArrayList<>();
        this.status = ProjectStatus.PLANNING;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public List<Task> getTasks() { return tasks; }
    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }
    public String getTeamLead() { return teamLead; }
    public void setTeamLead(String teamLead) { this.teamLead = teamLead; }
    public List<String> getTeamMembers() { return teamMembers; }
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }

    // Project Management Methods
    public void addTask(Task task) {
        task.setProjectId(this.id);
        tasks.add(task);
    }

    public void removeTask(String taskId) {
        tasks.removeIf(task -> task.getId().equals(taskId));
    }

    public void addTeamMember(String member) {
        if (!teamMembers.contains(member)) {
            teamMembers.add(member);
        }
    }

    public void removeTeamMember(String member) {
        teamMembers.remove(member);
    }

    public double getProjectProgress() {
        if (tasks.isEmpty()) return 0.0;
        long completedTasks = tasks.stream()
                .filter(task -> task.getStatus() == Task.Status.DONE)
                .count();
        return (double) completedTasks / tasks.size() * 100;
    }

    @Override
    public String toString() {
        return String.format("Project{id='%s', name='%s', status=%s, progress=%.1f%%}",
                id, name, status, getProjectProgress());
    }
} 