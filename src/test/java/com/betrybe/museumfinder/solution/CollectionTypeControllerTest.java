package com.betrybe.museumfinder.solution;


import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CollectionTypeService service;

  @Test
  @DisplayName("Returns correct object and status ok when typesList exists.")
  public void testGetCollectionTypesCountSuccess() throws Exception {
    String[] collectionTypes = {"hist", "imag"};

    CollectionTypeCount mockcCT = new CollectionTypeCount(collectionTypes, 492L);

    Mockito.when(service.countByCollectionTypes(any())).thenReturn(mockcCT);

    String url = "/collections/count/%s,%s".formatted("hist", "imag");

    ResultActions result = mockMvc.perform(get(url));

    result.andExpect(status().isOk());
    result.andExpect(jsonPath("$.collectionTypes").isNotEmpty());
    result.andExpect(jsonPath("$.collectionTypes").isArray());
    result.andExpect(jsonPath("$.collectionTypes[0]").value("hist"));
    result.andExpect(jsonPath("$.collectionTypes[1]").value("imag"));
    result.andExpect(jsonPath("$.count").value(492));

    Mockito.verify(service).countByCollectionTypes("hist,imag");

  }

  @Test
  @DisplayName("Returns not found status 404 when typesList does not exist.")
  public void testGetCollectionTypesCountNotFound() throws Exception {

    Mockito.when(service.countByCollectionTypes(any()))
        .thenReturn(new CollectionTypeCount(new String[]{}, 0));

    String url = "/collections/count/%s,%s".formatted("xxx", "zzz");

    ResultActions result = mockMvc.perform(get(url));

    result.andExpect(status().isNotFound());

    Mockito.verify(service).countByCollectionTypes("xxx,zzz");
  }

}