package com.example.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

@SpringBootTest
public class AddressBookTest {
    @Autowired
    AddressBookRepository repository;
    AddressBook a;
    BuddyInfo b;
    BuddyInfo b2;

    @BeforeEach
    public void setUp() {
        a = new AddressBook();
        b = new BuddyInfo("Jason", "222-222-2222");
        b2 = new BuddyInfo("John", "222-222-2222");
    }

    @Test
    public void test_add() {
        b.setAddressBook(a);
        b2.setAddressBook(a);
        a.addBuddy(b);
        a.addBuddy(b2);
        repository.save(a);

        long id = a.getId();
        AddressBook results = repository.findById(id);
        ArrayList<BuddyInfo> buddyInfos = new ArrayList<>(results.getBuddyInfo());

        assert(buddyInfos.get(0).getName().equals("Jason"));
        assert(buddyInfos.get(1).getName().equals("John"));
        assert(a.getAddressBookLength() == 2);
    }


    @Test
    public void test_delete() {
        b.setAddressBook(a);
        b2.setAddressBook(a);
        a.addBuddy(b);
        a.addBuddy(b2);
        a.removeBuddy(b);
        repository.save(a);

        long id = a.getId();
        AddressBook results = repository.findById(id);
        ArrayList<BuddyInfo> buddyInfos = new ArrayList<>(results.getBuddyInfo());

        assert(buddyInfos.get(0).getName().equals("John"));
        assert(a.getAddressBookLength() == 1);
    }

    @AfterEach
    public void tearDown() {
        a = null;
        b = null;
        b2 = null;
        repository.deleteAll();
    }
}
