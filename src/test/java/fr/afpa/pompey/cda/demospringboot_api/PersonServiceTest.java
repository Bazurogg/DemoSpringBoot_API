package fr.afpa.pompey.cda.demospringboot_api;

import fr.afpa.pompey.cda.demospringboot_api.model.Person;
import fr.afpa.pompey.cda.demospringboot_api.repository.PersonRepository;
import fr.afpa.pompey.cda.demospringboot_api.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testSavePerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person savedPerson = personService.savePerson(person);
        assertNotNull(savedPerson);
        assertEquals("John", savedPerson.getFirstName());
        assertEquals("Doe", savedPerson.getLastName());

        verify(personRepository, times(1)).save(person);
    }

    @Test
    void testGetPersonById() {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("John");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Optional<Person> foundPerson = personService.getPerson(1);
        assertTrue(foundPerson.isPresent());
        assertEquals("John", foundPerson.get().getFirstName());

        verify(personRepository, times(1)).findById(1);
    }
}
