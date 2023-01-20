package gbc.ca.comp3095teamcrackers.ShoppingCart;

import gbc.ca.comp3095teamcrackers.Recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    private RecipeService recipeService;



    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId) {
        recipeService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return "redirect:/all-recipes";
    }


    @RequestMapping(path ="/shoppingCart/removeProduct/{productId}", method = RequestMethod.GET)
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        recipeService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }


    @GetMapping("/favourite-items")
    public ModelAndView favouriteItems() {
        ModelAndView modelAndView = new ModelAndView("/favourite-items");
        modelAndView.addObject("products", shoppingCartService.getFavItems());
        return modelAndView;
    }


    @GetMapping("/addFav/{productId}")
    public String addProductToFav(@PathVariable("productId") Long productId) {
        recipeService.findById(productId).ifPresent(shoppingCartService::addFav);
        return "redirect:/all-recipes";
    }


}
