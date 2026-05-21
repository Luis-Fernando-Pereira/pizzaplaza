package br.com.pizzaplaza.product.factories;

import br.com.pizzaplaza.product.libraries.Category;
import br.com.pizzaplaza.product.libraries.Flavor;
import br.com.pizzaplaza.product.libraries.FlavorCategory;
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
