package com.petstore.petstore.web.controllers.Store;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import com.petstore.petstore.service.Pet.PetService;
import com.petstore.petstore.service.Store.StoreService;
import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
import com.petstore.petstore.web.exceptions.StoreNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@Slf4j
public class StoreRestController {


    @Autowired
    StoreService storeService;

    @Autowired
    PetService petService;

    @PostMapping("/create")
    public ResponseEntity<?> saveStore (@RequestBody Store store) {
        try {
            storeService.saveStore(store);
        } catch (NullPointerException exe){
            return  ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping("/addpet")
    public ResponseEntity<?> addPetToStore (@RequestParam("storeId") String storeId,
                                @RequestParam("petId") String petId) {

        log.info("Mapping pets requestt storeid--> {} petid -->{}", storeId, petId);
        Store store;
        try{
            store = storeService.mapPetToStore(Integer.parseInt(storeId), Integer.parseInt(petId));
        } catch (StoreNotFoundException | PetDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(store);
    }

//    @PatchMapping("/pet/{id}")
//    public Pet deletePetFromStore (@PathVariable Integer id) throws PetDoesNotExistException {
//        Pet pet = petService.findPetById(id);
//        pet.setStore(null);
//        petService.savePet(pet);
//        return pet;
//    }
//



}
