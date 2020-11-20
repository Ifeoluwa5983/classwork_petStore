package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.repository.PetRepository;

import com.petstore.petstore.web.exceptions.PetDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import  static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

//@SpringBootTest
//@Sql(scripts = {"classpath:db/insert.sql"})
class PetServiceImplTest {

    @Mock
    PetRepository petRepository;

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
    void mockTheDeleteByIdRepositoryTest(){
        doNothing().when(petRepository).deleteById(31);
        petService.deletePetById(31);
        verify(petRepository, times(1)).deleteById(31);
    }

    @Test
    void whenPetWithIdDoesNotExist_thenThrowException() {
        assertThrows(PetDoesNotExistException.class, ()-> petService.findPetById(7));
    }
}