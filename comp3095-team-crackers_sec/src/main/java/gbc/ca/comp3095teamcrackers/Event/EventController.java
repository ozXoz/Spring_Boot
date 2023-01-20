package gbc.ca.comp3095teamcrackers.Event;

/*
Comp3095-Team-Crackers-Assignment-2
        Team Members:
        Safa Aru :     101331910
        Hakan Inel :   101213098
        Onur Korkmaz : 101303363
        Ahmet Buyukbas: 101304595
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(path = "/my-events", method = RequestMethod.GET)
    public String getAllMyEvents(Model model){
        model.addAttribute("events", eventService.getAllEventsOfLoggedInUser());
        return "my-events";
    }


    @RequestMapping(path = "/delete-events/{id}", method = RequestMethod.GET)
    public String deleteEvents(@PathVariable Long id, Model model){
        eventService.deleteEvent(id);
        return "redirect:/my-events";
    }

    @RequestMapping(path = "/add-event", method = RequestMethod.GET)
    public String addEvent(Model model){
        model.addAttribute("event", new Event());
        return "add-event";
    }

    @RequestMapping(path = "/add-event", method = RequestMethod.POST)
    public String saveNewEvent(Event event, Model model) {
        eventService.addEvent(event);
        return "redirect:/my-events";
    }

    @RequestMapping(path = "/update-event/{id}", method = RequestMethod.GET)
    public String updateEventById(Model model, @PathVariable Long id) {
        model.addAttribute("event", eventService.getEventById(id));
        return "update-event";
    }

    @RequestMapping(path = "/update-event", method = RequestMethod.POST)
    public String updateEvent(Event event) {
        eventService.updateEvent(event);
        return "redirect:/my-events";
    }
}
