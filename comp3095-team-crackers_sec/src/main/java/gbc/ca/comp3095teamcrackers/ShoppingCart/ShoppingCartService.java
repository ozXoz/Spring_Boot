package gbc.ca.comp3095teamcrackers.ShoppingCart;

import gbc.ca.comp3095teamcrackers.Recipe.Recipe;

import java.util.Map;

/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

public interface ShoppingCartService {

    void addProduct(Recipe recipe);
    void removeProduct(Recipe recipe);
    Map<Recipe, Integer> getProductsInCart();

    void addFav(Recipe recipe);
    Map<Recipe, Integer> getFavItems();
}
