package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository implements Repository<Category> {

    private static final String FILE_NAME = "categories.json";
    private static final long START_ID = 1;

    private static CategoriesRepository instance;

    private List<Category> categories;

    private CategoriesRepository() {}

    public static synchronized CategoriesRepository getInstance() {
        if (instance == null) {
            instance = new CategoriesRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<Category> get() {
        return categories;
    }

    public Category get(long id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    @Override
    public Category add(Category object) {

        if (object == null) return null;

        Category category = new Category(object);
        category.setId(generateId());

        categories.add(category);

        return category;
    }

    @Override
    public Category update(Category object) {

        if (object == null) return null;

        Category category = new Category(object);
        remove(category);

        categories.add(category);

        return category;
    }

    @Override
    public void remove(Category object) {

        if (object == null) return;

        Category category = get(object.getId());
        ProductsRepository.getInstance().remove(object.getId());
        categories.remove(category);
    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            categories = JSONUtils.readListFromFile(FILE_NAME, Category.class);
        } catch (IOException e) {
            categories = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private long generateId() {
        long id = START_ID;
        for (Category category : categories) {
            id = Math.max(id, category.getId());
        }

        return ++id;
    }
}