package com.petstore.petstore.web.controllers.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.service.Pet.PetService;
import com.petstore.petstore.service.Store.StoreService;
import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pet")
@Slf4j
public class PetRestController {

    @Autowired
    PetService petService;

    @Autowired
    StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<?> savePet(@RequestBody Pet pet){

        try {
            petService.savePet(pet);
        } catch (NullPointerException exe){
            return  ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }


    @GetMapping("all")
    public ResponseEntity<?> findAllPets(){
        log.info("Get endpoint called");
        List<Pet> petList = petService.findPet();
        log.info("Retrieved pets from database --> {}", petList);
        return ResponseEntity.ok().body(petList);
    }

    @DeleteMapping("/one/{id}")
    public ResponseEntity<?> deletePetById(@PathVariable Integer id){

        try{
            petService.deletePetById(id);

        }catch (PetDoesNotExistException pex){
            return ResponseEntity.badRequest().body(pex.getMessage());
        }

        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/one/{id}")
    public @ResponseBody ResponseEntity updatePetById(@RequestBody Pet pet){


        try{
            pet = petService.updatePet(pet);
        } catch (PetDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(pet);
    }




    @GetMapping("one/{id}")
    public ResponseEntity<?> findPetById(@PathVariable Integer id) {
        log.info("Id of pet to be found --> {}", id);
        Pet pet = null;
//        try {
//           pet = petService.findPetById(id);
//
//        }catch (PetDoesNotExistException pex){
//            return ResponseEntity.badRequest().body(pex.getMessage());
//        }
//        log.info("Retrieved pet from database --> {}", pet);
        return  ResponseEntity.ok().body(pet);

    }
}
