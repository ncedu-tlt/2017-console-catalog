package ru.ncedu.menu.utils.ExportUtil.ExportModel;

import ru.ncedu.menu.models.Product;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;


public class ProductExport {

    private List<Product> products;

    public ProductExport(){}

    public ProductExport(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @XmlElement(name = "Product")
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
