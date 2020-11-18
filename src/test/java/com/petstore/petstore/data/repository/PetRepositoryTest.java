package com.petstore.petstore.data.repository;

import com.petstore.petstore.data.model.Gender;
import com.petstore.petstore.data.model.Pet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

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
}