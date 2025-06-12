package com.devtaskpro.view;

import com.devtaskpro.controller.TaskManager;
import com.devtaskpro.model.Project;
import com.devtaskpro.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private final TaskManager taskManager;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter;

    public CommandLineInterface() {
        this.taskManager = new TaskManager();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public void start() {
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleProjectManagement();
                    break;
                case "2":
                    handleTaskManagement();
                    break;
                case "3":
                    handleReports();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== DevTask Pro ===");
        System.out.println("1. Project Management");
        System.out.println("2. Task Management");
        System.out.println("3. Reports");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleProjectManagement() {
        while (true) {
            System.out.println("\n=== Project Management ===");
            System.out.println("1. Create Project");
            System.out.println("2. List Projects");
            System.out.println("3. View Project Details");
            System.out.println("4. Delete Project");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createProject();
                    break;
                case "2":
                    listProjects();
                    break;
                case "3":
                    viewProjectDetails();
                    break;
                case "4":
                    deleteProject();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleTaskManagement() {
        while (true) {
            System.out.println("\n=== Task Management ===");
            System.out.println("1. Create Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Update Task Status");
            System.out.println("4. Assign Task");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    createTask();
                    break;
                case "2":
                    listTasks();
                    break;
                case "3":
                    updateTaskStatus();
                    break;
                case "4":
                    assignTask();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleReports() {
        while (true) {
            System.out.println("\n=== Reports ===");
            System.out.println("1. Project Progress");
            System.out.println("2. Overdue Tasks");
            System.out.println("3. Tasks by Priority");
            System.out.println("4. Tasks by Status");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showProjectProgress();
                    break;
                case "2":
                    showOverdueTasks();
                    break;
                case "3":
                    showTasksByPriority();
                    break;
                case "4":
                    showTasksByStatus();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        System.out.print("Enter project description: ");
        String description = scanner.nextLine();

        Project project = taskManager.createProject(name, description);
        System.out.println("Project created successfully: " + project);
    }

    private void listProjects() {
        List<Project> projects = taskManager.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }

        System.out.println("\nProjects:");
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    private void viewProjectDetails() {
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        Project project = taskManager.getProject(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("\nProject Details:");
        System.out.println("Name: " + project.getName());
        System.out.println("Description: " + project.getDescription());
        System.out.println("Status: " + project.getStatus());
        System.out.println("Progress: " + project.getProjectProgress() + "%");
        System.out.println("\nTasks:");
        for (Task task : project.getTasks()) {
            System.out.println(task);
        }
    }

    private void deleteProject() {
        System.out.print("Enter project ID to delete: ");
        String projectId = scanner.nextLine();
        taskManager.deleteProject(projectId);
        System.out.println("Project deleted successfully.");
    }

    private void createTask() {
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task category: ");
        String category = scanner.nextLine();
        System.out.println("Select priority:");
        System.out.println("1. CRITICAL");
        System.out.println("2. HIGH");
        System.out.println("3. MEDIUM");
        System.out.println("4. LOW");
        System.out.print("Choose priority (1-4): ");
        
        Task.Priority priority;
        switch (scanner.nextLine()) {
            case "1": priority = Task.Priority.CRITICAL; break;
            case "2": priority = Task.Priority.HIGH; break;
            case "3": priority = Task.Priority.MEDIUM; break;
            case "4": priority = Task.Priority.LOW; break;
            default:
                System.out.println("Invalid priority. Using MEDIUM.");
                priority = Task.Priority.MEDIUM;
        }

        try {
            Task task = taskManager.createTask(projectId, title, description, priority, category);
            System.out.println("Task created successfully: " + task);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listTasks() {
        System.out.print("Enter project ID (or press Enter for all tasks): ");
        String projectId = scanner.nextLine();
        
        List<Task> tasks;
        if (projectId.isEmpty()) {
            tasks = taskManager.getTasksByStatus(Task.Status.TODO);
        } else {
            tasks = taskManager.getTasksByProject(projectId);
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("\nTasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void updateTaskStatus() {
        System.out.print("Enter task ID: ");
        String taskId = scanner.nextLine();
        System.out.println("Select new status:");
        System.out.println("1. TODO");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. REVIEW");
        System.out.println("4. DONE");
        System.out.print("Choose status (1-4): ");

        Task.Status status;
        switch (scanner.nextLine()) {
            case "1": status = Task.Status.TODO; break;
            case "2": status = Task.Status.IN_PROGRESS; break;
            case "3": status = Task.Status.REVIEW; break;
            case "4": status = Task.Status.DONE; break;
            default:
                System.out.println("Invalid status. No changes made.");
                return;
        }

        taskManager.updateTaskStatus(taskId, status);
        System.out.println("Task status updated successfully.");
    }

    private void assignTask() {
        System.out.print("Enter task ID: ");
        String taskId = scanner.nextLine();
        System.out.print("Enter username to assign: ");
        String username = scanner.nextLine();

        taskManager.assignTaskToUser(taskId, username);
        System.out.println("Task assigned successfully.");
    }

    private void showProjectProgress() {
        System.out.print("Enter project ID: ");
        String projectId = scanner.nextLine();
        double progress = taskManager.getProjectCompletionRate(projectId);
        System.out.printf("Project completion rate: %.1f%%\n", progress);
    }

    private void showOverdueTasks() {
        List<Task> overdueTasks = taskManager.getOverdueTasks();
        if (overdueTasks.isEmpty()) {
            System.out.println("No overdue tasks found.");
            return;
        }

        System.out.println("\nOverdue Tasks:");
        for (Task task : overdueTasks) {
            System.out.println(task);
        }
    }

    private void showTasksByPriority() {
        System.out.println("Select priority:");
        System.out.println("1. CRITICAL");
        System.out.println("2. HIGH");
        System.out.println("3. MEDIUM");
        System.out.println("4. LOW");
        System.out.print("Choose priority (1-4): ");

        Task.Priority priority;
        switch (scanner.nextLine()) {
            case "1": priority = Task.Priority.CRITICAL; break;
            case "2": priority = Task.Priority.HIGH; break;
            case "3": priority = Task.Priority.MEDIUM; break;
            case "4": priority = Task.Priority.LOW; break;
            default:
                System.out.println("Invalid priority.");
                return;
        }

        List<Task> tasks = taskManager.getTasksByPriority(priority);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with this priority.");
            return;
        }

        System.out.println("\nTasks with " + priority + " priority:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void showTasksByStatus() {
        System.out.println("Select status:");
        System.out.println("1. TODO");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. REVIEW");
        System.out.println("4. DONE");
        System.out.print("Choose status (1-4): ");

        Task.Status status;
        switch (scanner.nextLine()) {
            case "1": status = Task.Status.TODO; break;
            case "2": status = Task.Status.IN_PROGRESS; break;
            case "3": status = Task.Status.REVIEW; break;
            case "4": status = Task.Status.DONE; break;
            default:
                System.out.println("Invalid status.");
                return;
        }

        List<Task> tasks = taskManager.getTasksByStatus(status);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with this status.");
            return;
        }

        System.out.println("\nTasks with " + status + " status:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
} 