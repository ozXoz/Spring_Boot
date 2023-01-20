package gbc.ca.comp3095teamcrackers.Recipe;

import gbc.ca.comp3095teamcrackers.User.User;
import gbc.ca.comp3095teamcrackers.User.UserRepository;
import gbc.ca.comp3095teamcrackers.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/*
Comp3095-Team-Crackers-Assignment-1
Team Members:
    Safa Aru :     101331910
    Hakan Inel :   101213098
    Onur Korkmaz : 101303363
 */
@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    public Boolean addRecipe(Recipe recipe){
        User user = userService.getLoggedInUserProfile();
        Set<Recipe> recipes = user.getRecipes();
        recipes.add(recipe);
        recipe.setUser(user);
        user.setRecipes(recipes);
        userRepository.save(user);
        return true;
    }

    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).get();
    }

    public Boolean updateRecipe(Recipe recipeNewInfo){
        Recipe recipe = recipeRepository.findById(recipeNewInfo.getId()).get();
        recipe.setName(recipeNewInfo.getName());
        recipe.setDescription(recipeNewInfo.getDescription());
        recipe.setIngredients(recipeNewInfo.getIngredients());
        recipeRepository.save(recipe);
        return true;
    }

    public boolean deleteRecipe(Long id){
        Recipe recipe = getRecipeById(id);
        recipeRepository.delete(recipe);
        return true;
    }

    public Set<Recipe> getAllRecipesOfLoggedInUser(){
        User loggedInUser = userService.getLoggedInUserProfile();
        return loggedInUser.getRecipes();
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipesByName(String query){
        return recipeRepository.findAllByNameContaining(query);
    }

}
