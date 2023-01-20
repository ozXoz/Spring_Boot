package gbc.ca.comp3095teamcrackers.User;


/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

import gbc.ca.comp3095teamcrackers.Event.Event;
import gbc.ca.comp3095teamcrackers.MealPlan.PlanMeal;
import gbc.ca.comp3095teamcrackers.Recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
public class User implements UserDetails {

    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isActive = false;
    private String status = STATUS_INACTIVE;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Recipe> recipes = new HashSet<>();

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<PlanMeal> planMeals = new HashSet<>();

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    private LocalDate createdDate = LocalDate.now(Clock.systemUTC());

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public User(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    public String getPassword() {
        return this.password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public boolean isEnabled() {
        return this.isActive;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    public List<String> getRoles() {
        return roles;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public Set<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }


    public Set<PlanMeal> getPlanMeals() {
        return planMeals;
    }
    public void setPlanMeals(Set<PlanMeal> planMeals) {
        this.planMeals = planMeals;
    }

    public Set<Event> getEvents() {
        return events;
    }
    public void setEvents(Set<Event> events) { this.events = events; }
}
