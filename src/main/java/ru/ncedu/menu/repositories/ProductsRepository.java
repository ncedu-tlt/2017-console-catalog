package ru.ncedu.menu.repositories;

import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepository implements Repository<Product> {
    private static final String FILE_NAME = "products.json";
    private static final long START_ID = 0;

    private static ProductsRepository instance;

    private List<Product> products;

    private ProductsRepository() {
    }

    public static synchronized ProductsRepository getInstance() {
        if (instance == null) {
            instance = new ProductsRepository();
            instance.load();
        }
        return instance;
    }

    @Override
    public List<Product> get() {
        return products;
    }

    public Product get(long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product get(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    /*
     *  Выбор продуктов по отдельной категории. Временый коструктор.
     */
    public List<Product> get(long categoryId, String category) {

        List<Product> productsInCategory = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategoryId() == categoryId) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }

    @Override
    public Product add(Product object) {

        if (object == null) return null;

        Product product = new Product(object);
        product.setId(generateId());

        products.add(product);
        return product;
    }

    @Override
    public Product update(Product object) {

        if (object == null) return null;

        Product product = new Product(object);
        remove(product);

        products.add(product);

        return product;
    }

    @Override
    public void remove(Product object) {

        if (object == null) return;

        Product product = get(object.getId());

        products.remove(product);
    }
    public void remove(long categoryId) {

        List<Product> productsToRemove = new ArrayList<>();

        if (categoryId == 0) return;

        for (Product product : products){
            if (product.getCategoryId() == categoryId){
                productsToRemove.add(product);
            }
        }
        products.addAll(productsToRemove);
    }

    @Override
    public void save() {
        try {
            JSONUtils.writeToFile(FILE_NAME, products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
           products = JSONUtils.readListFromFile(FILE_NAME, Product.class);
        } catch (IOException e) {
            products = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private long generateId() {
        long id = START_ID;
        for (Product product : products) {
            id = Math.max(id, product.getId());
        }

        return ++id;
    }
}
