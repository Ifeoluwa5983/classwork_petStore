package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Gender;
import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }
    @Test
    public void whenPetIsSaved_thenReturnAPetId(){
       Pet pet = new Pet();
       pet.setAge(2);
       pet.setBreed("Dog");
       pet.setColor("Yellow");
       pet.setName("Jackie");
       pet.setPetSex(Gender.MALE);

       log.info("Pet instance before saving ==> {}", pet);

       petRepository.save(pet);

       assertThat(pet.getId()).isNotNull();
       log.info("Pet instance after saving ==> {}", pet);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    public void whenStoreIsMappedToPet_thenForeignKeyIsPresent(){

        Pet pet = new Pet();
        pet.setAge(1);
        pet.setBreed("Dog");
        pet.setColor("White");
        pet.setName("Mira");
        pet.setPetSex(Gender.FEMALE);

        log.info("Pet instance before saving ==> {}", pet);

        Store store = new Store();
        store.setName("Pet sellers");
        store.setContactNumber("09017488075");
        store.setLocation("Yaba");

        log.info("Pet instance before saving ==> {}", store);

        pet.setStore(store);

        petRepository.save(pet);

        log.info("Pet instance after saving ==> {}", pet);
        log.info("Pet instance after saving ==> {}", store);

        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIAddPetsToStore_thenICanFetchALIstOfPetsFromStore(){


        //create a store
        Store store = new Store();
        store.setName("Pet sellers");
        store.setContactNumber("09017488075");
        store.setLocation("Yaba");

        log.info("Store instance before saving ==> {}", store);

        //create 2 pets
        Pet jack = new Pet();
        jack.setAge(2);
        jack.setBreed("Dog");
        jack.setColor("Brown");
        jack.setName("Brownie");
        jack.setPetSex(Gender.MALE);

        Pet sally = new Pet();
        sally.setAge(2);
        sally.setBreed("Dog");
        sally.setColor("Red");
        sally.setName("Sally");
        sally.setPetSex(Gender.FEMALE);

        log.info("Pet instance before saving ==> {}", jack, sally);

        jack.setStore(store);
        sally.setStore(store);

        //map pet to store
        store.addPets(sally);
        store.addPets(jack);

        //save store
        storeRepository.save(store);

        log.info("Pet instance after saving ==> {}", sally, jack);
        log.info("Store instance after saving ==> {}", store);

        assertThat(jack.getId()).isNotNull();
        assertThat(sally.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(store.getPets()).isNotNull();
    }

    @Test
    public void whenFindAllPetIsCalled_thenReturnAllPetsInStore(){
        //find pets from store

        List<Pet> savedPets = petRepository.findAll();

        log.info("Fetched pets list from db--> {}", savedPets);
        //assert that pets exists
        assertThat(savedPets).isNotNull();
        assertThat(savedPets.size()).isEqualTo(7);
    }

    @Test
    public void updateExistingPetDetailsTest(){
        Pet gen = petRepository.findById(91).orElse(null);

        assertThat(gen).isNotNull();
        assertThat(gen.getName()).isEqualTo("gen");
        gen.setName("jilly");

        petRepository.save(gen);

        assertThat(gen.getName()).isEqualTo("jilly");
    }

    @Test
    public void whenIDeletePetFromDatabase_thenPetIsDeleted(){
        boolean result = petRepository.existsById(31);

        assertThat(result).isEqualTo(true);

        petRepository.deleteById(31);

        assertThat(petRepository.existsById(31)).isEqualTo(false);
    }
}