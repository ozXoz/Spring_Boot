package gbc.ca.comp3095teamcrackers.Event;

/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

import gbc.ca.comp3095teamcrackers.User.User;
import gbc.ca.comp3095teamcrackers.User.UserRepository;
import gbc.ca.comp3095teamcrackers.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    public Boolean addEvent(Event event){
        User user = userService.getLoggedInUserProfile();
        Set<Event> events = user.getEvents();
        events.add(event);
        event.setUser(user);
        user.setEvents(events);
        userRepository.save(user);
        return true;
    }



    public Event getEventById(Long id){
        return eventRepository.findById(id).get();
    }

    public Boolean updateEvent(Event eventNewInfo){
        Event event = eventRepository.findById(eventNewInfo.getId()).get();
        event.setPlan(eventNewInfo.getPlan());
        eventRepository.save(event);
        return true;
    }

    public boolean deleteEvent(Long id){
        Event event = getEventById(id);
        eventRepository.delete(event);
        return true;
    }

    public Set<Event> getAllEventsOfLoggedInUser(){
        User loggedInUser = userService.getLoggedInUserProfile();
        return loggedInUser.getEvents();
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public List<Event> searchEventsByName(String query){
        return eventRepository.findAllByPlanContaining(query);
    }


    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

}
