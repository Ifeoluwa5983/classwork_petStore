package com.petstore.petstore.service.Pet;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import  static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceImplTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService = new PetServiceImpl();

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
    void mockTheFindByIdRepositoryTest(){
        when(petRepository.findById(41)).thenReturn(Optional.of(testPets));
        petService.findPetById(41);
        verify(petRepository, times(1)).findById(41);
    }

    @Test
    void mockTheDeleteByIdRepositoryTest(){
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);
        verify(petRepository, times(1)).deleteById(2);
    }
    @Test
    void mockTheUpdatePetsRepositoryTest(){

    }
}