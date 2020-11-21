package com.petstore.petstore.service.Store;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import com.petstore.petstore.data.repository.PetRepository;
import com.petstore.petstore.data.repository.StoreRepository;
import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
import com.petstore.petstore.web.exceptions.StoreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {


    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PetRepository petRepository;

    @Override
    public List<Store> findStore() {
        return storeRepository.findAll();
    }

    @Override
    public Store saveStore(Store store) {
        if(store == null){
            throw new NullPointerException("Pet object cannot be null");
        }
        return storeRepository.save(store);
    }

    @Override
    public Store findStoreById(Integer id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public Store updateStore(Store store) {

        return storeRepository.save(store);
    }

    @Override
    public void deleteById(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Store mapPetToStore(Integer storeId, Integer petId) throws StoreNotFoundException, PetDoesNotExistException {

        Store savedStore = storeRepository.findById(storeId).orElse(null);
        if(savedStore == null){
            throw new StoreNotFoundException("Store with thee id: "+storeId+" does not exist");
        }

        Pet savedPet = petRepository.findById(petId).orElse(null);

        if(savedPet == null){
            throw new PetDoesNotExistException("Pet with the id:"+petId+" does not exists");
        }

        savedPet.setStore(savedStore);
        savedStore.addPets(savedPet);

        return storeRepository.save(savedStore);

    }
















}
