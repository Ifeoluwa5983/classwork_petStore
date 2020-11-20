package com.petstore.petstore.service.Store;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import com.petstore.petstore.data.repository.PetRepository;
import com.petstore.petstore.data.repository.StoreRepository;
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
    public Store mapPetToStore(Integer storeId, Pet pet) {
        Store store = storeRepository.findById(storeId).orElse(null);
//        Pet pet = petRepository.findById(petId).orElse(null);

        store.addPets(pet);
        storeRepository.save(store);

        return store;
    }
}
