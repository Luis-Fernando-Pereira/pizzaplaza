package br.com.pizzaplaza.product.factories;

import br.com.pizzaplaza.entity.Category;
import br.com.pizzaplaza.entity.Flavor;
import br.com.pizzaplaza.entity.FlavorCategory;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlavorCategoryFactory {
    public static FlavorCategory createFlavorCategory(Flavor flavor, Category category) {
        FlavorCategory flavorCategory = new FlavorCategory();
        flavorCategory.setFlavor(flavor);
        flavorCategory.setCategory(category);
        return flavorCategory;
    }
}
