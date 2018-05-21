package com.boot.service.event;

import com.boot.service.db.mongomodel.EventDetailsModel;
import com.boot.service.db.repositoryimpl.EventRepository;
import com.boot.service.db.repositoryimpl.EventRepositoryCustom;
import com.boot.service.model.EventDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Govind
 */
@RestController
@RequestMapping("/*/**/eventservice")
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventRepositoryCustom eventRepositoryCustom;

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public EventDetails getEvent(@PathVariable(name = "name") String name){
        EventDetailsModel model = eventRepository.getEventByName(name);

        if(model != null)
            return new EventDetails(model.getId(), model.getName(), model.getLocation(), model.getDescription());
        else
            return null;
    }


    @RequestMapping(value = "/get/{name}/{location}", method = RequestMethod.GET)
    public List<EventDetails> getEvent(@PathVariable(name = "name") String name,
                                 @PathVariable(name= "location",required = false) String location){
       // EventDetailsModel model = null;//eventRepository.getEventByName(name);

        if(Objects.nonNull(location)){
            List<EventDetailsModel> eventDetailsModelList =  eventRepositoryCustom.getEventsByNameAndLocation(name, location);
            return eventDetailsModelList.parallelStream().map(
                    eventDetailsModel ->
                    new EventDetails(eventDetailsModel.getId(), eventDetailsModel.getName(), eventDetailsModel.getLocation(), eventDetailsModel.getDescription())
            ).collect(Collectors.toList());
        }else{
            return null;
        }
    }
    /**
     *
     * @param details
     * @param name
     * @param location
     * @return
     */
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

    @RequestMapping(value="/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<EventDetails> listAllEvents(){
        List<EventDetails> listEvents = null;
        try {
            listEvents = eventRepository.findAll().parallelStream().map(eventDetailsModel -> new EventDetails(eventDetailsModel.getId(),
                    eventDetailsModel.getName(),
                    eventDetailsModel.getLocation(),
                    eventDetailsModel.getDescription())).collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        return listEvents;
    }
}
