package ru.ncedu.menu.utils.ExportUtil.ExportModel;

import ru.ncedu.menu.models.Category;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CategoryExport {

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public CategoryExport(List<Category> categories) {
        this.categories = categories;
    }

    @XmlElement(name = "Category")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
