package org;

import org.application.ITodoRepository;
import org.presentation.TodoController;
import org.persistence.inmemory.TodoInMemoryRepository;
import org.persistence.csvfile.TodoCsvFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String configFilePath = "config.txt"; // default
        if (args.length > 0 && args[0].startsWith("config=")) {
            configFilePath = args[0].split("=")[1];
        }

        String repoType = readRepoTypeFromConfig(configFilePath);
        ITodoRepository repository;

        switch (repoType.toLowerCase()) {
            case "csv":
                repository = new TodoCsvFileRepository();
                System.out.println("📁 Using CSV file storage from config");
                break;
            case "memory":
            default:
                repository = new TodoInMemoryRepository();
                System.out.println("⚡ Using in-memory storage from config");
                break;
        }

        TodoController controller = new TodoController(repository);

        controller.handleCreateTodo("Submit TP2", "2025-06-01");
        controller.handleCreateTodo("Prepare presentation", "");
        controller.handleListTodos();
    }

    private static String readRepoTypeFromConfig(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .filter(line -> line.startsWith("repo="))
                    .map(line -> line.split("=")[1])
                    .findFirst()
                    .orElse("memory");
        } catch (IOException e) {
            System.out.println("⚠️ Config file not found. Defaulting to memory storage.");
            return "memory";
        }
    }
}
