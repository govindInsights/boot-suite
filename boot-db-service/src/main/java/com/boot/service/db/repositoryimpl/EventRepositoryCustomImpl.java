package com.boot.service.db.repositoryimpl;

import com.boot.service.db.mongomodel.EventDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<EventDetailsModel> getEventsByNameAndLocation(String name, String location) {
        try{
            return mongoTemplate.find(Query.query(Criteria.where("name").in(name).andOperator(Criteria.where("location").in(location))), EventDetailsModel.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
