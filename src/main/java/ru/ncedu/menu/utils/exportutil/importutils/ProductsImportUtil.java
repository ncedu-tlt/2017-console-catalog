package ru.ncedu.menu.utils.exportutil.importutils;

import ru.ncedu.menu.models.Product;
import ru.ncedu.menu.repositories.PricesRepository;
import ru.ncedu.menu.repositories.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsImportUtil implements Import{
    private List<Product> products;
    private boolean updatingProducts;

    public ProductsImportUtil(List<Product> products, boolean updatingProducts) {
        if (products != null) {
            this.products = products;
        } else {
            this.products = new ArrayList<>();
        }
        this.updatingProducts = updatingProducts;
    }

    public void setInRepository() {
        List<Product> productsInRepository = ProductsRepository.getInstance().get();
        List<Product> updateProducts = new ArrayList<>();

        if (!productsInRepository.isEmpty()) {
            for (Product productInRepository : productsInRepository) {
                for (Product product : products) {
                    if (productInRepository.getId() == product.getId()) {
                        updateProducts.add(product);
                    }
                }
            }
            if (!updateProducts.isEmpty() && updatingProducts) {
                for (Product product : updateProducts) {
                    ProductsRepository.getInstance().update(product);
                }
            }
            products.removeAll(updateProducts);
            if (!products.isEmpty()) {
                for (Product product : products) {
                    ProductsRepository.getInstance().add(product);
                }
            }
        } else if (productsInRepository.isEmpty() && !products.isEmpty()) {
            for (Product product : products) {
                ProductsRepository.getInstance().add(product);
            }
        }
    }

}
