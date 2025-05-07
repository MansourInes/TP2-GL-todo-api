package org;

import java.time.LocalDate;

public class Todo {
    private final String name;
    private final LocalDate dueDate;

    public Todo(String name, LocalDate dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
