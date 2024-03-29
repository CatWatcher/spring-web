package com.example.springweb.controllers;

import com.example.springweb.entity.Event;
import com.example.springweb.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/event/add", method = RequestMethod.GET)
    public String showFrom(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", new Event());
        return "add_event";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute Event event, Model model){
        eventRepository.save(event);
        return "add_event";
    }

    // спецификации
    @RequestMapping(value = "/event/info", method = RequestMethod.GET)
    public String infoForm(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "event_info";
    }

    @RequestMapping(value = "/event/info", method = RequestMethod.POST)
    public String showEventInfo (@RequestParam(name = "eventId",
                                defaultValue = "1",
                                required = false) int eventId, Model model) {

        //eventRepository.findOne(eventByTitle("title"));

        model.addAttribute("event", eventRepository.findById(eventId));

        return "event_info";
    }
}
