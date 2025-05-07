package org;

public class Main {
    public static void main(String[] args) {
        TodoController controller = new TodoController();


        controller.handleCreateTodo("Learn Java", "2025-06-01");
        controller.handleCreateTodo("Learn Java", "2025-06-02"); // Doublon
        controller.handleCreateTodo("Buy Milk", "bad-date"); // Mauvais format
        controller.handleCreateTodo("A very very very long name that exceeds sixty-three characters, it will fail", null);
        controller.handleListTodos();
    }
}

