package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.repository.PetRepository;
import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    public Pet updatePet(Pet pet) throws PetDoesNotExistException {

        log.info("Pet Request object --> {}", pet);
        Pet savedPet = petRepository.findById(pet.getId()).orElse(null);

        if(savedPet != null){

            if(pet.getName() != null){
                savedPet.setName(pet.getName());
            }

            if(pet.getAge() != null){
                savedPet.setAge(pet.getAge());
            }

            if(pet.getBreed() != null){
                savedPet.setBreed(pet.getBreed());
            }

            if(pet.getColor() != null){
                savedPet.setColor(pet.getColor());
            }

            if(pet.getPetSex() != null){
                savedPet.setPetSex(pet.getPetSex());
            }

            log.info("Before saving pet  object --> {}", savedPet);
            return petRepository.save(savedPet);
        }
        else {
            throw new PetDoesNotExistException("Pet with the Id: "+ pet.getId() +" Does not exsit");
        }
    }

    @Override
    public void deletePetById(Integer id) throws PetDoesNotExistException {
       try {
           petRepository.deleteById(id);
       }catch (Exception exe){
           throw new PetDoesNotExistException("\"Pet with the id:\" + id + \" does not exist and cannot be deleted\"");
       }
    }
}
