package org.persistence.csvfile;

import org.application.ITodoRepository;
import org.Todo;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoCsvFileRepository implements ITodoRepository {

    private final Path csvFilePath;

    public TodoCsvFileRepository() {
        this.csvFilePath = Paths.get("todos.csv");

        try {
            Path parent = csvFilePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (!Files.exists(csvFilePath)) {
                Files.createFile(csvFilePath);
            }

            System.out.println("📄 Saving todos to file: " + csvFilePath.toAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize CSV file", e);
        }
    }

    @Override
    public void addTodo(Todo todo) {
        try (BufferedWriter writer = Files.newBufferedWriter(csvFilePath, StandardOpenOption.APPEND)) {
            writer.write(todo.getName() + "," + (todo.getDueDate() != null ? todo.getDueDate() : "") + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to CSV", e);
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(csvFilePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String name = parts[0];
                LocalDate due = (parts.length > 1 && !parts[1].isEmpty()) ? LocalDate.parse(parts[1]) : null;
                todos.add(new Todo(name, due));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from CSV", e);
        }
        return todos;
    }

    @Override
    public boolean isDuplicate(String name) {
        return getAllTodos().stream()
                .anyMatch(todo -> todo.getName().equalsIgnoreCase(name));
    }
}
