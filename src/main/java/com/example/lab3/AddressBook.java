package com.example.lab3;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "addressBook", cascade = CascadeType.ALL)
    private Collection<BuddyInfo> buddyInfo;

    public Long getId() {
        return id;
    }

    public AddressBook() {
        this.buddyInfo = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddyObj) {
        buddyInfo.add(buddyObj);
    }

    public void removeBuddy(BuddyInfo buddyObj) {
        buddyInfo.remove(buddyObj);
    }

    public int getAddressBookLength() { return buddyInfo.size(); }

    public Collection<BuddyInfo> getBuddyInfo() {
        return this.buddyInfo;
    }

    public void printAddressBook() {
        for (BuddyInfo b : buddyInfo) {
            System.out.println(b);
        }
    }

    @PrePersist
    @PreUpdate
    public void updateAddressAssociation(){
        for(BuddyInfo buddyInfo1 : this.buddyInfo){
            buddyInfo1.setAddressBook(this);
        }
    }

    public static void main(String[] args) {

    }
}
