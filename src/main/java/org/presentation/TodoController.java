package org.presentation;

import org.application.ITodoRepository;
import org.application.TodoService;
import org.Todo;

public class TodoController {

    private final TodoService service;

    public TodoController(ITodoRepository repository) {
        this.service = new TodoService(repository);
    }

    public void handleCreateTodo(String name, String dueDateStr) {
        try {
            String result = service.createTodo(name, dueDateStr);
            System.out.println(result.startsWith("Created") ? "✅ [201 CREATED] " + result
                    : "❌ [400 BAD REQUEST] " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ [400 BAD REQUEST] " + e.getMessage());
        }
    }

    public void handleListTodos() {
        for (Todo t : service.getAllTodos()) {
            System.out.println("- " + t.getName() + (t.getDueDate() != null ? " (Due: " + t.getDueDate() + ")" : ""));
        }
    }
}
