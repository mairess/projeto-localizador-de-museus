package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CollectionTypeServiceTest {

  @Autowired
  CollectionTypeService service;

  @MockBean
  MuseumFakeDatabase database;

  @Test
  @DisplayName("Returns correct object when typesList exists.")
  public void testGetCollectionTypesCountSuccess() {

    String[] collectionTypes = {"hist", "imag"};

    CollectionTypeCount mockcCT = new CollectionTypeCount(collectionTypes, 492L);

    Mockito.when(database.countByCollectionType(any())).thenReturn(246L);

    CollectionTypeCount ctc = service.countByCollectionTypes("hist, imag");

    assertNotNull(ctc);
    assertEquals(mockcCT.count(), ctc.count());
    assertArrayEquals(mockcCT.collectionTypes(), ctc.collectionTypes());

    Mockito.verify(database).countByCollectionType("hist");
    Mockito.verify(database).countByCollectionType("imag");

  }

}