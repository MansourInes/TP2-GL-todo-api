package org.application;

import org.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TodoService {

    private final ITodoRepository repository;

    public TodoService(ITodoRepository repository) {
        this.repository = repository;
    }

    public String createTodo(String name, String dueDateStr) {
        if (!isValidName(name)) return "Invalid: Name is required and must be < 64 characters.";
        if (repository.isDuplicate(name)) return "Invalid: Name must be unique.";

        LocalDate dueDate = parseDate(dueDateStr);
        Todo todo = new Todo(name, dueDate);
        repository.addTodo(todo);
        return "Created successfully.";
    }

    public boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 63;
    }

    private LocalDate parseDate(String dueDateStr) {
        try {
            return (dueDateStr == null || dueDateStr.trim().isEmpty()) ? null : LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
        }
    }

    public Iterable<Todo> getAllTodos() {
        return repository.getAllTodos();
    }
}
