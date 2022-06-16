package pl.altkom.scah.dogservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import pl.altkom.scah.dogservice.controller.model.CreateDogRequest;
import pl.altkom.scah.dogservice.controller.model.Dog;
import pl.altkom.scah.dogservice.controller.model.UpdateDogRequest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSingleDogAndHttpStatusOk() throws Exception {

        //given

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/dog/50"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final Dog dog = jsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(dog.getId()).isEqualTo(50L);
        Assertions.assertThat(dog.getName()).isEqualTo("Sonia");
        Assertions.assertThat(dog.getBreed()).isEqualTo("Golden Retriever");
    }

    @Order(1)
    @Test
    void shouldReturnAllDogsAndHttpStatusOk() throws Exception {

        //given

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/dog"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final List<Dog> dogs = jsonToListOfObjects(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(dogs).isNotEmpty();
        Assertions.assertThat(dogs.size()).isEqualTo(1000);
    }

    @Test
    void shouldReturnHttpStatusNotFound() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/dog/11150"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        //then
    }

    @Test
    void shouldCreateAndSaveDogAndReturnStatusOk() throws Exception {

        //given
        final String requuest = objectToJson(new CreateDogRequest("Azor", LocalDate.now(), "Labrador", 1L));

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dog")
                .content(requuest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final Dog dog = jsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(dog.getName()).isEqualTo("Azor");
        Assertions.assertThat(dog.getBreed()).isEqualTo("Labrador");
        Assertions.assertThat(dog.getOwnerId()).isEqualTo(1L);
    }

    @Test
    void shouldUpdateDogAndReturnStatusOk() throws Exception {

        //given
        final String requuest = objectToJson(new UpdateDogRequest("Azor", LocalDate.now(), "Labrador", 1L));

        //when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/dog/1")
                .content(requuest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //then
        final Dog dog = jsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertThat(dog.getId()).isEqualTo(1L);
        Assertions.assertThat(dog.getName()).isEqualTo("Azor");
        Assertions.assertThat(dog.getBreed()).isEqualTo("Labrador");
        Assertions.assertThat(dog.getOwnerId()).isEqualTo(1L);
    }

    private String objectToJson(final Object obj) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    private Dog jsonToObject(final String json) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, Dog.class);
    }

    private List<Dog> jsonToListOfObjects(final String json) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, new TypeReference<List<Dog>>() {
        });
    }
}