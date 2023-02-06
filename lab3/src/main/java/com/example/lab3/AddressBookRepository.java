package com.example.lab3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "addressBook")
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findById(@RequestParam("id") long id);
}
