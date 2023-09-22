package com.example.demo.controller;

import com.example.demo.models.PassportWagon;
import com.example.demo.models.TrainComposition;
import com.example.demo.service.TrainCompositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(TrainCompositionController.class)
public class TrainCompositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TrainCompositionService trainCompositionService;

    @InjectMocks
    private TrainCompositionController trainCompositionController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void rearrangeWagons() throws Exception {
        // Подготовим данные для запроса
        Long trainCompositionId = 1L;
        List<Long> wagonIds = new ArrayList<>();
        wagonIds.add(1L);
        wagonIds.add(2L);
        String destination = "Destination";

        // Мок сервиса
        doNothing().when(trainCompositionService).rearrangeWagons(trainCompositionId, wagonIds, destination);

        // Выполнение POST-запроса для операции перестановки вагонов
        mockMvc.perform(MockMvcRequestBuilders.post("/api/train-compositions/rearrange/{trainCompositionId}", trainCompositionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"wagonIds\":[1,2],\"destination\":\"Destination\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Wagons rearranged successfully."))
                .andDo(print());
    }

    @Test
    void receiveWagons() throws Exception {
        // Подготовим список вагонов
        List<PassportWagon> wagons = new ArrayList<>();
        // Добавим один вагон в список
        PassportWagon wagon = new PassportWagon();
        wagon.setNumber("123");
        wagon.setType("Type1");
        wagon.setCapacity(100.0);
        wagon.setTareWeight(50.0);
        wagons.add(wagon);

        // Мок сервиса
        when(trainCompositionService.createTrainComposition(wagons)).thenReturn(new TrainComposition());

        // Выполнение POST-запроса для приема вагонов
        mockMvc.perform(MockMvcRequestBuilders.post("/api/train-compositions/receive")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wagons)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void departFromStation() throws Exception {
        // Mock сервиса
        doNothing().when(trainCompositionService).departureFromStation(anyLong());

        // Выполнение запроса POST для убытия вагонов
        mockMvc.perform(MockMvcRequestBuilders.post("/api/train-compositions/depart/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Wagons departed successfully."))
                .andDo(print());
    }
}
