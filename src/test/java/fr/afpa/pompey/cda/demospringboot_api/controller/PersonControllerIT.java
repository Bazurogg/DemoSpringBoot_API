package fr.afpa.pompey.cda.demospringboot_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.afpa.pompey.cda.demospringboot_api.model.Person;
import fr.afpa.pompey.cda.demospringboot_api.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        personRepository.deleteAll();
    }

    @Test
    void testCreatePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testGetAllPersons() throws Exception {
        Person person = new Person();
        person.setFirstName("Alice");
        person.setLastName("Smith");
        personRepository.save(person);

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Alice"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"));
    }

    @Test
    void testGetPersonById() throws Exception {
        Person person = new Person();
        person.setFirstName("Bob");
        person.setLastName("Marley");
        person = personRepository.save(person);

        mockMvc.perform(get("/person/" + person.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"))
                .andExpect(jsonPath("$.lastName").value("Marley"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Eve");
        person.setLastName("Brown");
        person = personRepository.save(person);

        person.setFirstName("Eve Updated");

        mockMvc.perform(put("/person/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Eve Updated"));
    }

    @Test
    void testDeletePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Mike");
        person.setLastName("Tyson");
        person = personRepository.save(person);

        mockMvc.perform(delete("/person/" + person.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/person/" + person.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // La réponse doit être vide
    }
}
