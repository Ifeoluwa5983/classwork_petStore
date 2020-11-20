package com.petstore.petstore.service.Store;

import com.petstore.petstore.data.model.Pet;
import com.petstore.petstore.data.model.Store;
import com.petstore.petstore.data.repository.PetRepository;
import com.petstore.petstore.data.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import  static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class StoreServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @Autowired
    PetRepository petRepository;

    @InjectMocks
    StoreService storeService = new StoreServiceImpl();

    Store myStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        myStore = new Store();
    }

    @Test
    void saveStoreToTheRepositoryTest(){
        when(storeRepository.save(myStore)).thenReturn(myStore);
        storeService.saveStore(myStore);
        verify(storeRepository, times(1)).save(myStore);
    }

    @Test
    void findStoreById () {
        when(storeRepository.findById(2)).thenReturn(Optional.of(myStore));
        storeService.findStoreById(2);
        verify(storeRepository, times(1)).findById(2);
    }

    @Test
    void deleteById () {
        doNothing().when(storeRepository).deleteById(2);
        storeService.deleteById(2);
        verify(storeRepository, times(1)).deleteById(2);
    }

//    @Test
//    void findAllStores () {
//        when(storeRepository.findAll()).thenReturn( myStore);
//        storeService.findStore();
//        verify(storeRepository, times(1)).findAll();
//    }
}