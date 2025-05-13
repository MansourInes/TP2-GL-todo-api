package org;

import org.application.TodoService;
import org.persistence.TodoStorage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;


public class TodoServiceTest {

    @Before
    public void clearTodos() {
        TodoStorage.clear();
    }

    @Test
    public void testValidName() {
        assertTrue(TodoService.isValidName("Buy milk"));
        assertFalse(TodoService.isValidName(null));
        assertFalse(TodoService.isValidName("   "));
        assertFalse(TodoService.isValidName(new String(new char[64]).replace("\0", "a")));
    }

    @Test
    public void testCreateTodoWithValidData() {
        String response = TodoService.createTodo("Task 1", "2025-06-01");
        assertEquals("Created successfully.", response);

        List<Todo> todos = TodoStorage.getAllTodos();
        assertEquals(1, todos.size());
        assertEquals("Task 1", todos.get(0).getName());
    }

    @Test
    public void testCreateTodoWithDuplicateName() {
        TodoService.createTodo("Unique Task", null);
        String response = TodoService.createTodo("Unique Task", null);
        assertEquals("Invalid: Name must be unique.", response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTodoWithInvalidDate() {
        TodoService.createTodo("Task", "not-a-date");
    }

    @Test
    public void testCreateTodoWithEmptyDate() {
        String response = TodoService.createTodo("No Due Date", "");
        assertEquals("Created successfully.", response);
    }
}
