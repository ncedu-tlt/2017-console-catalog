package ru.ncedu.menu.models;

import java.io.Serializable;

public class Category implements Serializable {

    private long id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Category category) {
        this(category.getId(), category.getName());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getName(long id) { return name; }

    public void setName(String name) {
        this.name = name;
    }
}