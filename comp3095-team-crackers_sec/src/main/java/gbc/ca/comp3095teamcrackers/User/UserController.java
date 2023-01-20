package gbc.ca.comp3095teamcrackers.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;

/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

@CrossOrigin(maxAge = 3600)
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/")
    public String getHome(){
        return "home";
    }

    @RequestMapping(path = "/login")
    public String login(){
        return "login";
    }


    @RequestMapping(path = "/create-account", method = RequestMethod.GET)
    public String getSignupPage(Model model){

        model.addAttribute("status", null);
        model.addAttribute("user", new User());

        return "create-account";
    }
    @RequestMapping(path = "/create-account", method = RequestMethod.POST)
    public String createAccount(User newUserDetail, Model model) {
        LinkedHashMap<String,String> response = userService.createAccount(newUserDetail);
        String templateName = "";
        if (response.get("status").equalsIgnoreCase("200")){
            model.addAttribute("status", response.get("status"));
            model.addAttribute("fullName", response.get("fullName"));
            model.addAttribute("message", response.get("message"));
            templateName = "thankyou";
        }else {
            model.addAttribute("status", response.get("status"));
            model.addAttribute("message", response.get("message"));
            templateName = "create-account";
        }
        return templateName;
    }

    @RequestMapping(path = "/view-profile", method = RequestMethod.GET)
    public String getUserProfile(Model model){
        model.addAttribute("user", userService.getLoggedInUserProfile());
        return "view-profile";
    }

    @RequestMapping(path = "/update-profile/{id}", method = RequestMethod.GET)
    public String updateRecipeById(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.getLoggedInUserProfile());
        return "update-profile";
    }

    @RequestMapping(path = "/update-profile", method = RequestMethod.POST)
    public String updateRecipe(User user) {
        userService.updateUser(user);
        return "redirect:/login";
    }
}
