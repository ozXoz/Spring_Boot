package gbc.ca.comp3095teamcrackers.Recipe;

import gbc.ca.comp3095teamcrackers.User.User;

import javax.persistence.*;

/*
Comp3095-Team-Crackers-Assignment-1
Team Members:
    Safa Aru :     101331910
    Hakan Inel :   101213098
    Onur Korkmaz : 101303363
 */
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String ingredients;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
