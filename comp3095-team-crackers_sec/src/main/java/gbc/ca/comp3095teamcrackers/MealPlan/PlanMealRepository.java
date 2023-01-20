package gbc.ca.comp3095teamcrackers.MealPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/
@Repository
public interface PlanMealRepository extends JpaRepository<PlanMeal, Long> {


}
