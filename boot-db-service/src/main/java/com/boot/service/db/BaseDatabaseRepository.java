package com.boot.service.db;


import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

public class BaseDatabaseRepository extends JpaRepositoryFactory{


    public BaseDatabaseRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public boolean save(){

        return false;
    }
}
