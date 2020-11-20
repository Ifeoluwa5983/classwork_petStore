package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.web.exceptions.PetDoesNotExistException;

import java.util.List;

public interface PetService {

    List<Pet> findPet();
    Pet savePet(Pet pet);
    Pet findPetById(Integer Id) throws PetDoesNotExistException;
    Pet updatePet(Pet pet);
    void deletePetById(Integer id);
}
