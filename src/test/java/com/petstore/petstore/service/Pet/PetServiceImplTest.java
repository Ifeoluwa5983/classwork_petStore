package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.repository.PetRepository;

import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import  static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})
@Slf4j
class PetServiceImplTest {

    @Mock
    PetRepository petRepository;

    @Autowired
    PetRepository petRepositoryImpl;

    @InjectMocks
    PetService petService = new PetServiceImpl();

    @Autowired
    PetService petServiceImpl;

    Pet testPets;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testPets = new Pet();
    }

    @Test
    void mockTheSavePetToRepositoryTest(){
        when(petRepository.save(testPets)).thenReturn(testPets);
        petService.savePet(testPets);
        verify(petRepository, times(1)).save(testPets);
    }

    @Test
    @Disabled
    void mockTheFindByIdRepositoryTest() throws PetDoesNotExistException {
        when(petRepository.findById(41)).thenReturn(Optional.of(testPets));
        petService.findPetById(41);
        verify(petRepository, times(1)).findById(41);
    }

    @Test
    void mockTheDeleteByIdRepositoryTest() throws PetDoesNotExistException {
        doNothing().when(petRepository).deleteById(31);
        petService.deletePetById(31);
        verify(petRepository, times(1)).deleteById(31);
    }

    @Test
    void whenPetWithIdDoesNotExist_thenThrowException() {
        assertThrows(PetDoesNotExistException.class, ()-> petService.findPetById(7));
    }

    @Test
    void updatePetDetailsTest(){

        Pet savedPet = petRepositoryImpl.findById(31).orElse(null);
        log.info("Pet object --> {}", savedPet);
        assertThat(savedPet).isNotNull();

        savedPet.setAge(43);
        petRepositoryImpl.save(savedPet);
        log.info("Pet object saved --> {}", savedPet);
    }


















}