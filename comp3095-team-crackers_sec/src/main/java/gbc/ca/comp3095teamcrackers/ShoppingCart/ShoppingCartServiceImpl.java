package gbc.ca.comp3095teamcrackers.ShoppingCart;

import gbc.ca.comp3095teamcrackers.Recipe.Recipe;
import gbc.ca.comp3095teamcrackers.Recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private RecipeRepository recipeRepository;

    private Map<Recipe, Integer> recipes = new HashMap<>();

    private Map<Recipe, Integer> favourites = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void addProduct(Recipe recipe) {

        if (recipes.containsKey(recipe)) {
            recipes.replace(recipe, recipes.get(recipe.getId()));
        } else {
            recipes.put(recipe, recipes.get(recipe.getId()));
        }
    }

    @Override
    public void removeProduct(Recipe recipe) {
        if (recipes.containsKey(recipe)) {
            recipes.remove(recipe);
        }

    }
    @Override
    public Map<Recipe, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(recipes);
    }

    @Override
    public void addFav(Recipe recipe) {

        if (favourites.containsKey(recipe)) {
            favourites.replace(recipe, favourites.get(recipe) + 1);
        } else {
            favourites.put(recipe, 1);
        }

    }
    @Override
    public Map<Recipe, Integer> getFavItems() {
        return favourites;
    }
}
