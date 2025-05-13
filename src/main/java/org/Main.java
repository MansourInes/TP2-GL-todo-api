package org;

import org.presentation.TodoController;
import org.persistence.inmemory.TodoInMemoryRepository;

public class Main {
    public static void main(String[] args) {
        TodoInMemoryRepository repository = new TodoInMemoryRepository();
        TodoController controller = new TodoController(repository);

        controller.handleCreateTodo("Buy eggs", "2025-06-01");
        controller.handleCreateTodo("Buy eggs", null); // duplicate
        controller.handleCreateTodo("Walk dog", "");   // no date
        controller.handleListTodos();
    }
}
