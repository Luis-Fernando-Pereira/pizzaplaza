package br.com.pizzaplaza.product.factories;

import br.com.pizzaplaza.product.entities.Category;
import br.com.pizzaplaza.product.entities.Flavor;
import br.com.pizzaplaza.product.entities.FlavorCategory;
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
