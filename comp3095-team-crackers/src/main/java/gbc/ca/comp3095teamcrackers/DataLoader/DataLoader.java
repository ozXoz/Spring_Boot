/*********************************************************************************
// Project: < Recipe Project>
        * Assignment: < Assignment 1 >
        * Author(s): < Hamza Koc, Kazi Hasanus Safa, Resul Yuksektepe>
        * Student Number: < 101274109, 101275458,101259410  >
        * Date:
        * Description: <Adds some dummy data when project runs>

//*********************************************************************************/



package gbc.ca.comp3095teamcrackers.DataLoader;
import gbc.ca.comp3095teamcrackers.Recipe.Recipe;
import gbc.ca.comp3095teamcrackers.User.User;
import gbc.ca.comp3095teamcrackers.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Comp3095-Team-Crackers-Assignment-1
Team Members:
    Safa Aru :     101331910
    Hakan Inel :   101213098
    Onur Korkmaz : 101303363
 */

public class DataLoader implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("admin");
        user.setLastName("test");
        user.setUsername("admin@test.test");
        user.setPassword(passwordEncoder.encode("admin12345"));
        user.setActive(true);
        user.setStatus(User.STATUS_ACTIVE);
        user.setRoles(List.of("USER"));

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(createRecipe(user));
        user.setRecipes(recipes);
        userRepository.save(user);
    }

    private Recipe createRecipe(User user){
        Recipe recipe = new Recipe();
        recipe.setName("Pizza");
        recipe.setDescription("pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, ");
        recipe.setIngredients("The most common ingredient in pizza dishes is olive oil. Flour, yeast, mozzarella cheese, white sugar, tomatoes and onion are also common ingredients in pizza recipes.");
        recipe.setUser(user);
        return recipe;
    }
}
