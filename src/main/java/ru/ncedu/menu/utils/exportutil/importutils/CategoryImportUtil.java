package ru.ncedu.menu.utils.exportutil.importutils;

import ru.ncedu.menu.models.Category;
import ru.ncedu.menu.repositories.CategoriesRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryImportUtil  implements Import{
    private List<Category> categories;
    private boolean updatingCategory;

    public CategoryImportUtil(List<Category> categories, boolean updatingCategory) {
        if (categories != null){
            this.categories = categories;
        }else {
            this.categories = new ArrayList<>();
        }
        this.updatingCategory = updatingCategory;
    }

    public void setInRepository() {
        List<Category> categoriesInRepository = CategoriesRepository.getInstance().get();
        List<Category> updateCategory = new ArrayList<>(); // TODO: updateCategories

        if (!categoriesInRepository.isEmpty()) {
            for (Category categoryInRepository : categoriesInRepository) {
                for (Category category : categories) {
                    if (categoryInRepository.getId() == category.getId()) {
                        updateCategory.add(category);
                    }
                }
            }
            if (!updateCategory.isEmpty() && updatingCategory) { // TODO: следовало проверить еще в первом условии
                for (Category category : updateCategory) {
                    CategoriesRepository.getInstance().update(category);
                }
            }
            categories.removeAll(updateCategory);
            if (!categories.isEmpty()) {
                for (Category category : categories) {
                    CategoriesRepository.getInstance().add(category);
                }
            }
        } else if (categoriesInRepository.isEmpty() && !categories.isEmpty()) { //TODO: зачем еще раз проверять categoriesInRepository на пустоту?
            for (Category category : categories) {
                CategoriesRepository.getInstance().add(category);
            }
        }
    }
}
