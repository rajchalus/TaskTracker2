package com.devtaskpro.util;

import com.devtaskpro.model.Project;
import com.devtaskpro.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveProjects(List<Project> projects, String filename) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), projects);
    }

    public static List<Project> loadProjects(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<Project>>() {});
    }

    public static void saveTasks(List<Task> tasks, String filename) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), tasks);
    }

    public static List<Task> loadTasks(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<Task>>() {});
    }
} 