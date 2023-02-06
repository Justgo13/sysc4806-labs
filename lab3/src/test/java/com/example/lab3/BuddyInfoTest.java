package com.example.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
public class BuddyInfoTest {
    @Autowired
    BuddyInfoRepository repository;
    BuddyInfo b;

    @BeforeEach
    public void setUp() {

    }
    @Test
    public void test_query() {
        // save a few BuddyInfos
        repository.save(new BuddyInfo("Jason", "111-111-1111"));
        repository.save(new BuddyInfo("John", "111-111-1111"));

        BuddyInfo b = repository.findByName("Jason");
        BuddyInfo b2 = repository.findByName("John");
        assert(b != null);
        assert(b2 != null);
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        b = null;
    }
}
