package com.example.lab3;

import jakarta.persistence.*;

@Entity(name = "BuddyInfo")
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_book_id")
    private AddressBook addressBook;

    private String name;
    private String telephone;

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public BuddyInfo() {

    }

    public BuddyInfo(String name, String telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() { return telephone; }

    @Override
    public String toString() {
        return "Name: " + this.name + " Telephone: " + this.telephone;
    }
}