package org;

import java.util.List;

public class TodoController {

    public void handleCreateTodo(String name, String dueDateStr) {
        try {
            String result = TodoService.createTodo(name, dueDateStr);
            if (result.startsWith("Created")) {
                System.out.println("✅ [201 CREATED] " + result);
            } else {
                System.out.println("❌ [400 BAD REQUEST] " + result);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ [400 BAD REQUEST] " + e.getMessage());
        }
    }

    public void handleListTodos() {
        List<Todo> todos = TodoStorage.getAllTodos();
        System.out.println("📋 [200 OK] Listing all Todos:");
        for (Todo t : todos) {
            System.out.println("- " + t.getName() + (t.getDueDate() != null ? " (Due: " + t.getDueDate() + ")" : ""));
        }
    }
}
