package gbc.ca.comp3095teamcrackers.MealPlan;

import gbc.ca.comp3095teamcrackers.User.User;
import gbc.ca.comp3095teamcrackers.User.UserRepository;
import gbc.ca.comp3095teamcrackers.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/*
Comp3095-Team-Crackers-Assignment-1
Team Members:
    Safa Aru :     101331910
    Hakan Inel :   101213098
    Onur Korkmaz : 101303363
 */
@Service
public class PlanMealService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private PlanMealRepository planMealRepository;

    public boolean addPlanMeal(PlanMeal planMeal){
        User user = userService.getLoggedInUserProfile();
        Set<PlanMeal> planMeals = user.getPlanMeals();
        planMeals.add(planMeal);
        planMeal.setUser(user);
        user.setPlanMeals(planMeals);
        userRepository.save(user);
        return true;
    }

    public Set<PlanMeal> getAllPlannedMeals(){
        User user = userService.getLoggedInUserProfile();
        return user.getPlanMeals();
    }

    public boolean deletePlanMeal(Long id){
        PlanMeal planMeal = planMealRepository.findById(id).get();
        planMealRepository.delete(planMeal);
        return true;
    }

}
