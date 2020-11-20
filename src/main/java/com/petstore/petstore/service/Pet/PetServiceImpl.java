package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.repository.PetRepository;
import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
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
    public Pet findPetById(Integer id) throws PetDoesNotExistException {

        if(petRepository.existsById(id)){
            return petRepository.findById(id).get();
        }
        else throw new PetDoesNotExistException("Pet with the id:" + id + "does not exist");

    }


    @Override
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Integer id) {
        petRepository.deleteById(id);
    }
}
