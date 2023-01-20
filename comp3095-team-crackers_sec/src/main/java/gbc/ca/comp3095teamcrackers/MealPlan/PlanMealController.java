package gbc.ca.comp3095teamcrackers.MealPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

@Controller
public class PlanMealController {

    @Autowired
    private PlanMealService planMealService;

    @RequestMapping(path = "/delete-meal-plan/{id}", method = RequestMethod.GET)
    public String deletePlanMeal(@PathVariable Long id){
        planMealService.deletePlanMeal(id);
        return "redirect:/meal-plan";
    }

    @RequestMapping(path = "/meal-plan", method = RequestMethod.GET)
    public String getAllPlanMeals(Model model){
        model.addAttribute("mealPlans", planMealService.getAllPlannedMeals());
        return "meal-plan";
    }

    @RequestMapping(path = "/add-meal-plan", method = RequestMethod.GET)
    public String addPlanMeal(Model model){
        model.addAttribute("planMeal", new PlanMeal());
        return "add-meal-plan";
    }

    @RequestMapping(path = "/add-meal-plan", method = RequestMethod.POST)
    public String saveNewMealPlan(PlanMeal planMeal) {
        planMealService.addPlanMeal(planMeal);
        return "redirect:/meal-plan";
    }
}

