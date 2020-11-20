package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    Store findByName(String name);
}
