package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;

import java.util.List;

public interface PetService {

    List<Pet> findPet();
    Pet savePet(Pet pet);
    Pet findPetById(Integer id);
    Pet updatePet(Pet pet);
    void deletePetById(Integer id);
}
