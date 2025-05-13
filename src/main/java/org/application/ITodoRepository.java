package org.application;

import org.Todo;
import java.util.List;

public interface ITodoRepository {
    void addTodo(Todo todo);
    List<Todo> getAllTodos();
    boolean isDuplicate(String name);
}
