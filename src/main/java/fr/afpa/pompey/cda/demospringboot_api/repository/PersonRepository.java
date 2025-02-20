package fr.afpa.pompey.cda.demospringboot_api.repository;

import fr.afpa.pompey.cda.demospringboot_api.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    // No code needed here
    // Calling @Repository give access to CRUD
}