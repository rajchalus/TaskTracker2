package com.devtaskpro;

import com.devtaskpro.controller.TaskManager;
import com.devtaskpro.model.Project;
import com.devtaskpro.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
    @Test
    public void testCreateProjectAndTask() {
        TaskManager manager = new TaskManager();
        Project project = manager.createProject("Test Project", "A project for testing");
        assertNotNull(project);
        assertEquals("Test Project", project.getName());

        Task task = manager.createTask(project.getId(), "Test Task", "A task for testing", Task.Priority.HIGH, "Feature");
        assertNotNull(task);
        assertEquals("Test Task", task.getTitle());
        assertEquals(Task.Priority.HIGH, task.getPriority());
        assertEquals(project.getId(), task.getProjectId());
    }
} 