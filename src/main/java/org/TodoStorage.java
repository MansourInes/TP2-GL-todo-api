package org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoStorage {
    private static final List<Todo> todos = new ArrayList<>();

    public static boolean addTodo(Todo todo) {
        if (isDuplicate(todo.getName())) return false;
        todos.add(todo);
        return true;
    }

    public static List<Todo> getAllTodos() {
        return Collections.unmodifiableList(todos);
    }

    public static boolean isDuplicate(String name) {
        return todos.stream().anyMatch(t -> t.getName().equalsIgnoreCase(name));
    }

    // for tests
    public static void clear() {
        todos.clear();
    }
}
