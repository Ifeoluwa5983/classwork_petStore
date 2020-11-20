package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {


    @Autowired
    PetRepository petRepository;

    @Override
    public List<Pet> findPet() {
        return petRepository.findAll();
    }

    @Override
    public Pet savePet(Pet pet) {
        if(pet == null){
            throw new NullPointerException("Pet object cannot be null");
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet findPetById(Integer id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet updatePet(Pet pet) {
        pet.setId(0);

        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Integer id) {
        petRepository.deleteById(id);
    }
}
