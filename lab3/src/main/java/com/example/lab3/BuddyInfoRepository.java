package com.example.lab3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
@RepositoryRestResource(path = "buddy")
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    BuddyInfo findById(@RequestParam("id") long id);

    BuddyInfo findByName(@RequestParam("name") String name);
}
