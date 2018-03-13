package com.boot.service.db.mongomodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="event-details")
public class EventDetailsModel {

    public EventDetailsModel() {
    }

    public EventDetailsModel(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public EventDetailsModel(String id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    @Id
    public String id;

    @Indexed
    public String name;


    public String location;


    public String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
