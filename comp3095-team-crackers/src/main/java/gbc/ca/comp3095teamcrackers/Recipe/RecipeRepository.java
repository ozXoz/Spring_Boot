package gbc.ca.comp3095teamcrackers.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Comp3095-Team-Crackers-Assignment-1
Team Members:
    Safa Aru :     101331910
    Hakan Inel :   101213098
    Onur Korkmaz : 101303363
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByNameContaining(String query);
}
