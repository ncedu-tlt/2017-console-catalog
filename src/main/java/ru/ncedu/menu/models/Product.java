package ru.ncedu.menu.models;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private long categoryId;
    private String description;

    public Product() {}

    public Product(long id) {
        this.id = id;
    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(long id, String name, long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Product(long id, String name, long categoryId, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Product(Product product) {
        this(product.getId(), product.getName(),
                product.getCategoryId(), product.getDescription());
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

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
