package com.boot.service.db.repositoryimpl;

import java.util.List;

public interface EventRepositoryCustom<E> {

    public List<E> getEventsByNameAndLocation(String name, String location);
}
