package com.example.lab3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AddressBookController {
    AddressBookRepository addressBookRepository;
    public AddressBookController(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }
    @RequestMapping(value = "/index")
    public String index(@RequestParam Long id,  Model model) {
        Optional<AddressBook> a = addressBookRepository.findById(id);
        if (a.isPresent()) {
            model.addAttribute("addressBook", a.get().getBuddyInfo());
        }
        return "index";
    }
}
