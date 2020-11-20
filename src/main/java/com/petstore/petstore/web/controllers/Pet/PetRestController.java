package com.petstore.petstore.web.controllers.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.service.Pet.PetService;
import com.petstore.petstore.service.Store.StoreService;
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
        petService.deletePetById(id);

        Map<String, String> data = new HashMap<>();
        data.put("status", "success");
        data.put("message", "Deleted pet successfully");
        return null;
    }

    @PatchMapping("/{id}")
    public @ResponseBody Map<String, String> updatePetById(@RequestBody Pet pet){
        petService.savePet(pet);

        Map<String, String> response = new HashMap<>();

        response.put("message", "Update Successfully");
        response.put("status", "success");

        return response;
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
