package com.example.demo.controller;

import com.example.demo.models.PassportWagon;
import com.example.demo.service.PassportWagonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(PassportWagonController.class)
@AutoConfigureMockMvc
public class PassportWagonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper для преобразования объектов в JSON

    @MockBean
    private PassportWagonService passportWagonService;

    private List<PassportWagon> passportWagons;
    private PassportWagon samplePassportWagon;

    @BeforeEach
    void setUp() {
        // Создаем тестовые данные
        passportWagons = new ArrayList<>();

        PassportWagon passportWagon1 = new PassportWagon();
        passportWagon1.setId(1L);
        passportWagon1.setNumber("Wagon1");
        passportWagon1.setType("Type1");

        PassportWagon passportWagon2 = new PassportWagon();
        passportWagon2.setId(2L);
        passportWagon2.setNumber("Wagon2");
        passportWagon2.setType("Type2");

        passportWagons.add(passportWagon1);
        passportWagons.add(passportWagon2);

        samplePassportWagon = new PassportWagon();
        samplePassportWagon.setId(3L);
        samplePassportWagon.setNumber("Wagon3");
        samplePassportWagon.setType("Type3");
    }

    @Test
    void getAllPassportWagons() throws Exception {
        when(passportWagonService.getAllPassportWagons()).thenReturn(passportWagons);

        mockMvc.perform(get("/api/passport-wagons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].number").value("Wagon1"))
                .andExpect(jsonPath("$[1].number").value("Wagon2"));

        verify(passportWagonService, times(1)).getAllPassportWagons();
    }

    @Test
    void getPassportWagonById() throws Exception {
        Long id = 1L;
        when(passportWagonService.getPassportWagonById(id)).thenReturn(Optional.of(passportWagons.get(0)));

        mockMvc.perform(get("/api/passport-wagons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("Wagon1"));

        verify(passportWagonService, times(1)).getPassportWagonById(id);
    }

    @Test
    void createPassportWagon() throws Exception {
        when(passportWagonService.createPassportWagon(any(PassportWagon.class))).thenReturn(samplePassportWagon);

        mockMvc.perform(post("/api/passport-wagons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePassportWagon)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number").value("Wagon3"));

        verify(passportWagonService, times(1)).createPassportWagon(any(PassportWagon.class));
    }

    @Test
    void updatePassportWagon() throws Exception {
        Long id = 1L;
        PassportWagon updatedPassportWagon = passportWagons.get(0); // Обновленный вагон

        when(passportWagonService.updatePassportWagon(eq(id), any(PassportWagon.class)))
                .thenReturn(updatedPassportWagon);

        mockMvc.perform(put("/api/passport-wagons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePassportWagon)))
                .andExpect(status().isOk()) // Проверяет, что возвращается 200 OK
                .andExpect((ResultMatcher) jsonPath("$.number", is(updatedPassportWagon.getNumber())));

        verify(passportWagonService, times(1)).updatePassportWagon(eq(id), any(PassportWagon.class));
    }

    @Test
    void deletePassportWagon() throws Exception {
        Long id = 1L;
        doNothing().when(passportWagonService).deletePassportWagon(id);

        mockMvc.perform(delete("/api/passport-wagons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(passportWagonService, times(1)).deletePassportWagon(id);
    }
}