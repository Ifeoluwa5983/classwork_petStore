package com.petstore.petstore.service.Store;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;

import java.util.List;

public interface StoreService {

    List<Store> findStore();
    Store saveStore(Store store);
    Store findStoreById(Integer id);
    Store updateStore(Store store);
    void deleteById(Integer id);

    Store mapPetToStore (Integer storeId, Pet pet);

}
