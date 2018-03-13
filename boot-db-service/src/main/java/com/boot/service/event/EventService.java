package com.boot.service.event;

import com.boot.service.db.mongomodel.EventDetailsModel;
import com.boot.service.db.repositoryimpl.EventRepository;
import com.boot.service.model.EventDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Govind
 */
@RestController
@RequestMapping("/*/**/eventservice")
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public EventDetails getEvent(@PathVariable(name = "name") String name){
        EventDetailsModel model = eventRepository.getEventByName(name);
        if(model != null)
        return new EventDetails(model.getId(), model.getName(), model.getLocation(), model.getDescription());
        else
            return null;

    }

    @RequestMapping(value = "/add/{name}/{location}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addEvent(@RequestParam(value = "event", required = false)EventDetails details,
                            @PathVariable(value = "name") String name,
                            @PathVariable(value = "location") String location){
        if(details != null)
            eventRepository.insert(new EventDetailsModel(details.getId(), details.getName(), details.getLocation(), details.getDescription()));
        else
            eventRepository.insert(new EventDetailsModel(name, location));
        return true;
    }
}
