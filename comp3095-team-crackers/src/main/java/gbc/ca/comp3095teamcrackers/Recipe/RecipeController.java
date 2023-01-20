package gbc.ca.comp3095teamcrackers.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
Comp3095-Team-Crackers-Assignment-1
Team Members:
    Safa Aru :     101331910
    Hakan Inel :   101213098
    Onur Korkmaz : 101303363
 */
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(path = "/my-recipes", method = RequestMethod.GET)
    public String getAllMyRecipes(Model model){
        model.addAttribute("recipes", recipeService.getAllRecipesOfLoggedInUser());
        return "my-recipes";
    }

    @RequestMapping(path = "/all-recipes", method = RequestMethod.GET)
    public String getAllRecipes(Model model){
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "all-recipes";
    }

    @RequestMapping(path = "/search-recipes", method = RequestMethod.GET)
    public String searchRecipes(Model model, String query){
        model.addAttribute("query", query);
        if (query != null){
            model.addAttribute("recipes", recipeService.searchRecipesByName(query));
        }
        return "search-recipes";
    }

    @RequestMapping(path = "/delete-recipes/{id}", method = RequestMethod.GET)
    public String deleteRecipe(@PathVariable Long id, Model model){
        recipeService.deleteRecipe(id);
        return "redirect:/my-recipes";
    }

    @RequestMapping(path = "/add-recipe", method = RequestMethod.GET)
    public String addRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "add-recipe";
    }

    @RequestMapping(path = "/add-recipe", method = RequestMethod.POST)
    public String saveNewRecipe(Recipe recipe, Model model) {
        recipeService.addRecipe(recipe);
        return "redirect:/my-recipes";
    }

    @RequestMapping(path = "/update-recipe/{id}", method = RequestMethod.GET)
    public String updateRecipeById(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "update-recipe";
    }

    @RequestMapping(path = "/update-recipe", method = RequestMethod.POST)
    public String updateRecipe(Recipe recipe) {
        recipeService.updateRecipe(recipe);
        return "redirect:/my-recipes";
    }
}
