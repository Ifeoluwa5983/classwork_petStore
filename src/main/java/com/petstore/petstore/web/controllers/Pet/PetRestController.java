package com.petstore.petstore.web.controllers.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import com.petstore.petstore.data.repository.StoreRepository;
import com.petstore.petstore.service.Pet.PetService;
import com.petstore.petstore.service.Store.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    @GetMapping("/pets")
    public List<Pet> findAllPets(){
        return petService.findPet();
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Map<String, String> deletePetById(@PathVariable Integer id){
        petService.deletePetById(id);

        Map<String, String> data = new HashMap<>();
        data.put("status", "success");
        data.put("message", "Deleted pet successfully");
        return data;
    }

    @PatchMapping("/{id}")
    public @ResponseBody Pet updatePetById(@RequestBody Pet pet){
        petService.savePet(pet);

//        Map<String, Gene> response = new HashMap<>();
//
//        response.put("message", "Update Successfully");
//        response.put("status", "success");

        return pet;
    }
}
