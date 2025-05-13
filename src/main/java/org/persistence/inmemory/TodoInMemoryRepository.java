package org.persistence.inmemory;

import org.application.ITodoRepository;
import org.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoInMemoryRepository implements ITodoRepository {

    private final List<Todo> todos = new ArrayList<>();

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return Collections.unmodifiableList(todos);
    }

    @Override
    public boolean isDuplicate(String name) {
        return todos.stream().anyMatch(t -> t.getName().equalsIgnoreCase(name));
    }

    public void clear() {
        todos.clear();
    }
}
