package org;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TodoService {

    public static String createTodo(String name, String dueDateStr) {
        if (!isValidName(name)) return "Invalid: Name is required and must be < 64 characters.";
        if (TodoStorage.isDuplicate(name)) return "Invalid: Name must be unique.";

        LocalDate dueDate = parseDate(dueDateStr);
        Todo todo = new Todo(name, dueDate);

        boolean added = TodoStorage.addTodo(todo);
        return added ? "Created successfully." : "Error: Could not add todo.";
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 63;
    }

    private static LocalDate parseDate(String dueDateStr) {
        try {
            return (dueDateStr == null || dueDateStr.trim().isEmpty()) ? null : LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
        }
    }
}
