package org;

import org.application.TodoService;
import org.persistence.inmemory.TodoInMemoryRepository;
import org.Todo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TodoServiceTest {

    private TodoService service;
    private TodoInMemoryRepository repository;

    @Before
    public void setUp() {
        repository = new TodoInMemoryRepository();
        service = new TodoService(repository);
    }

    @Test
    public void testValidName() {
        assertTrue(service.isValidName("Buy milk"));
        assertFalse(service.isValidName(null));
        assertFalse(service.isValidName("   "));
        assertFalse(service.isValidName(new String(new char[64]).replace("\0", "a")));
    }

    @Test
    public void testCreateTodoWithValidData() {
        String response = service.createTodo("Task 1", "2025-06-01");
        assertEquals("Created successfully.", response);

        List<Todo> todos = repository.getAllTodos();
        assertEquals(1, todos.size());
        assertEquals("Task 1", todos.get(0).getName());
    }

    @Test
    public void testCreateTodoWithDuplicateName() {
        service.createTodo("Unique Task", null);
        String response = service.createTodo("Unique Task", null);
        assertEquals("Invalid: Name must be unique.", response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTodoWithInvalidDate() {
        service.createTodo("Task", "not-a-date");
    }

    @Test
    public void testCreateTodoWithEmptyDate() {
        String response = service.createTodo("No Due Date", "");
        assertEquals("Created successfully.", response);
    }
}
