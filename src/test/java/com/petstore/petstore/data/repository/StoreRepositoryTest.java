package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Gender;
import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PetRepository petRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveStoreToTheDatabase(){
        Store store = new Store();
        store.setName("sweet pet store");
        store.setLocation("Sabo");
        store.setContactNumber("09012345678");

        storeRepository.save(store);


        log.info("Store after saving", store);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void testForFindStores(){
        List<Store> stores = storeRepository.findAll();

        log.info("Fetched pets list from db--> {}", stores);

        assertThat(stores).isNotNull();

        assertThat(stores.size()).isEqualTo(6);

        log.info("new stores", stores);

        log.info("pets --> {}", stores.get(0).getPets());

    }
    @Test
    public void updateExistingStore(){
        Store store = storeRepository.findById(23).orElse(null);

        assertThat(store).isNotNull();

        store.setName("lucky pets");

        storeRepository.save(store);

        log.info("store after updating", store);
    }

    @Test
    public void deleteStoreFromDatabase(){
        assertThat(storeRepository.existsById(23)).isTrue();

        storeRepository.deleteById(23);

        assertThat(storeRepository.existsById(23)).isFalse();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void findAllPetsForAStore(){
        Store store = storeRepository.findById(22).orElse(null);

        List<Pet> pets = store.findPets(store);
//        List<Pet> pets = store.getPets();

        assertThat(pets).isNotNull();

        log.info("pets in the database --> {}", pets);
    }

    @Test
    public void findStoreByName(){
        Store store = storeRepository.findByName("my store");

        log.info("store object --> {}", store);

        assertThat(store).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void justTest () {
//        List<Pet> pets = petRepository.findAll();
        Pet pet = petRepository.findById(71).orElse(null);

        Store store = new Store();

        store.setName("Opera Store");
        store.setContactNumber("09032377733");
        store.setLocation("Sabo");

        storeRepository.save(store);

        log.info("Store id --> {}", store.getId());

        Pet bill = new Pet();
        pet.setAge(3);
        pet.setBreed("dog");
        pet.setColor("blue");
        pet.setPetSex(Gender.FEMALE);
        pet.setName("bill");



//        log.info("Pet {}", pet);
//        Store store = storeRepository.findById(pet.getStore().getId()).orElse(null);
//
//        log.info("Store {}", store);
    }


}